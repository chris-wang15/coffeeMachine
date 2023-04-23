package com.coffeeit.coffeemachine.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.modle.CoffeeExtraInfo
import com.coffeeit.coffeemachine.modle.CoffeeMachine
import com.coffeeit.coffeemachine.modle.CoffeeOrder
import com.coffeeit.coffeemachine.modle.event.ChooseEvent
import com.coffeeit.coffeemachine.modle.state.DataState
import com.coffeeit.coffeemachine.repository.MainRepository
import com.coffeeit.coffeemachine.utils.EventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * activity view model, for connecting machine & getting machine info & store order state
 */
class DataViewModel : ViewModel() {

    private val mainRepository = MainRepository

    private val _connectState = MutableStateFlow<DataState<String>>(DataState.Rest)
    val connectState = _connectState.asStateFlow()

    private val _machineState = MutableStateFlow<DataState<CoffeeMachine>>(DataState.Rest)
    val machineState = _machineState.asStateFlow()

    var coffeeOrder: CoffeeOrder = CoffeeOrder("")
        private set

    var machineInfo: CoffeeMachine? = null
        private set

    init {
        viewModelScope.launch {
            _connectState.collectLatest {
                if (it is DataState.Success) {
                    coffeeOrder = CoffeeOrder(it.data)
                }
            }
        }
        viewModelScope.launch {
            _machineState.collectLatest {
                machineInfo = if (it is DataState.Success) {
                    it.data
                } else {
                    null
                }
            }
        }
        observeEvent()
    }

    fun connectMachine() {
        if (_connectState.value is DataState.Loading) {
            Log.i(TAG, "still on loading, wait last connection")
            return
        }
        if (_connectState.value is DataState.Rest || _connectState.value is DataState.Error) {
            viewModelScope.launch(Dispatchers.IO) {
                mainRepository.tryConnectMachine().onEach { state ->
                    this@DataViewModel._connectState.value = state
                }
                    .launchIn(viewModelScope)
            }
        } else {
            Log.e(TAG, "not connected to machine yet ${_connectState.value}")
        }
    }

    fun fetchMachineInfo() {
        val connect = connectState.value
        if (connect !is DataState.Success
            || _machineState.value is DataState.Loading
        ) {
            Log.e(TAG, "Fetch on wrong state connect: $connect, machine: ${_machineState.value}")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getCoffeeMachineInfo(connect.data).onEach { state ->
                this@DataViewModel._machineState.value = state
            }
                .launchIn(viewModelScope)
        }
    }

    private fun observeEvent() {
        viewModelScope.launch {
            EventBus.subscribe<ChooseEvent> {
                when (it) {
                    is ChooseEvent.ChooseType -> {
                        coffeeOrder.typeId = it.data.getId()
                        coffeeOrder.typeName = it.data.getName()
                        navigateTo(it.v, R.id.SizeSelectionFragment, Bundle())
                    }
                    is ChooseEvent.ChooseSize -> {
                        coffeeOrder.sizeId = it.data.getId()
                        coffeeOrder.sizeName = it.data.getName()
                        navigateTo(it.v, R.id.ExtraSelectionFragment, Bundle())
                    }
                    is ChooseEvent.ChooseExtra -> {
                        if (it.data.getSub().size != 1) {
                            Log.e(TAG, "Error ChooseExtra Event: ${it.data}")
                            return@subscribe
                        }
                        val lastValue = coffeeOrder.extras.findLast { extraInfo ->
                            extraInfo.extraId == it.data.getId()
                        }
                        if (lastValue == null) {
                            val previousList = coffeeOrder.extras
                            coffeeOrder.extras = ArrayList<CoffeeExtraInfo>().apply {
                                add(
                                    CoffeeExtraInfo(
                                        it.data.getId(),
                                        it.data.getName(),
                                        it.data.getSub()[0].getId(),
                                        it.data.getSub()[0].getName()
                                    )
                                )
                                addAll(previousList)
                            }
                        } else {
                            lastValue.extraName = it.data.getName()
                            lastValue.subId = it.data.getSub()[0].getId()
                            lastValue.subName = it.data.getSub()[0].getName()
                        }
                    }
                }
            }
        }
    }

    private fun navigateTo(v: View, @IdRes resId: Int, bundle: Bundle) {
        v.findNavController().navigate(resId, bundle)
    }

    fun resetOrder() {
        val id = coffeeOrder.machineId
        coffeeOrder = CoffeeOrder(id)
    }

    fun resetExtraInfo() {
        coffeeOrder.extras = ArrayList()
    }

    companion object {
        private const val TAG = "DataViewModel"
    }
}
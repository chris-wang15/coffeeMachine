package com.coffeeit.coffeemachine.ui.overview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.modle.CoffeeMachine
import com.coffeeit.coffeemachine.modle.CoffeeOrder
import com.coffeeit.coffeemachine.modle.data.*
import com.coffeeit.coffeemachine.repository.MainRepository
import com.coffeeit.coffeemachine.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OverviewPageViewModel : BaseViewModel() {

    override val recyclerVisibility = MutableStateFlow(View.VISIBLE).asStateFlow()

    override val brewButtonVisibility = MutableStateFlow(View.VISIBLE).asStateFlow()

    fun getInfoListData(machineInfo: CoffeeMachine?, order: CoffeeOrder): List<SelectionDataType> {
        if (machineInfo == null || order.machineId == "") {
            Log.e(TAG, "wrong data $machineInfo & $order")
            return emptyList()
        }
        val typeData = TypeData(order.typeId, order.typeName)
        val sizeData = SizeData(order.sizeId, order.sizeName)
        val list = ArrayList<SelectionDataType>()
        list.add(typeData)
        list.add(sizeData)
        order.extras.forEach { tmpData ->
            val extraData = ExtraData(
                tmpData.extraId, tmpData.extraName,
                arrayListOf(SubData(tmpData.subId, tmpData.subName))
            )
            list.add(extraData)
        }
        return list
    }

    fun onCLickBrewButton(order: CoffeeOrder, view: View) {
        if (order.machineId == "" || order.typeId == "" || order.sizeId == "") {
            Log.e(TAG, "wrong order & $order")
            Toast.makeText(
                view.context,
                "Something wrong here, please order again",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            MainRepository.sendCoffeeOrder(order)
        }
        val bundle = Bundle()
        bundle.putParcelable(
            COFFEE_ORDER,
            order
        )
        view.findNavController().navigate(R.id.overview_page_to_success_page, bundle)
    }

    companion object {
        private const val TAG = "ExtraPageViewModel"
    }
}
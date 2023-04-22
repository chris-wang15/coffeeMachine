package com.coffeeit.coffeemachine.ui.extra

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.modle.CoffeeMachine
import com.coffeeit.coffeemachine.modle.CoffeeOrder
import com.coffeeit.coffeemachine.modle.data.ExtraData
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// TODO save checked settings
class ExtraPageViewModel : BaseViewModel() {

    override val recyclerVisibility = MutableStateFlow(View.VISIBLE).asStateFlow()

    override val brewButtonVisibility: StateFlow<Int> = MutableStateFlow(View.VISIBLE).asStateFlow()

    fun getExtraListData(machineInfo: CoffeeMachine?, order: CoffeeOrder): List<SelectionDataType> {
        if (machineInfo == null || order.machineId == "") {
            Log.e(TAG, "wrong data $machineInfo & $order")
            return emptyList()
        }
        val typeData = machineInfo.typeMap[order.typeId] ?: return emptyList()
        val list = ArrayList<ExtraData>()
        typeData.extras.forEach { tmpData ->
            machineInfo.extraMap[tmpData]?.let {
                list.add(it)
            }
        }
        return list
    }

    fun onCLickBrewButton(view: View?) {
        view?.findNavController()?.navigate(R.id.OverviewFragment, Bundle())
    }

    companion object {
        private const val TAG = "ExtraPageViewModel"
    }
}
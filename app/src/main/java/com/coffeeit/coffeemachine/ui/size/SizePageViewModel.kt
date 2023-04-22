package com.coffeeit.coffeemachine.ui.size

import android.util.Log
import android.view.View
import com.coffeeit.coffeemachine.modle.CoffeeMachine
import com.coffeeit.coffeemachine.modle.CoffeeOrder
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.modle.data.SizeData
import com.coffeeit.coffeemachine.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SizePageViewModel : BaseViewModel() {

    override val recyclerVisibility = MutableStateFlow(View.VISIBLE).asStateFlow()

    fun getSizeListData(machineInfo: CoffeeMachine?, order: CoffeeOrder): List<SelectionDataType> {
        if (machineInfo == null || order.machineId == "") {
            Log.e(TAG, "wrong data $machineInfo & $order")
            return emptyList()
        }
        val typeData = machineInfo.typeMap[order.typeId] ?: return emptyList()
        val list = ArrayList<SizeData>()
        typeData.sizes.forEach { tmpData ->
            machineInfo.sizeMap[tmpData]?.let {
                list.add(it)
            }
        }
        return list
    }

    companion object {
        private const val TAG = "SizePageViewModel"
    }
}
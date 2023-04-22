package com.coffeeit.coffeemachine.ui.type

import android.view.View
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.modle.event.ChooseEvent
import com.coffeeit.coffeemachine.ui.base.BaseViewHolder

class TypeViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun createChooseEvent(data: SelectionDataType): ChooseEvent {
        return ChooseEvent.ChooseType(data, itemView)
    }
}
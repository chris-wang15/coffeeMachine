package com.coffeeit.coffeemachine.ui.size

import android.view.View
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.modle.event.ChooseEvent
import com.coffeeit.coffeemachine.ui.base.BaseViewHolder

class SizeViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun createChooseEvent(data: SelectionDataType): ChooseEvent {
        return ChooseEvent.ChooseSize(data, itemView)
    }
}
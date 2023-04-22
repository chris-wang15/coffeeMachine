package com.coffeeit.coffeemachine.ui.extra

import android.view.View
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.modle.event.ChooseEvent
import com.coffeeit.coffeemachine.ui.base.BaseViewHolder

class ExtraViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun createChooseEvent(data: SelectionDataType): ChooseEvent {
        return ChooseEvent.ChooseExtra(data, itemView)
    }
}
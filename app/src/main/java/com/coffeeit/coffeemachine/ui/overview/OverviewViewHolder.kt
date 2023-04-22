package com.coffeeit.coffeemachine.ui.overview

import android.view.View
import android.widget.RadioButton
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.modle.event.ChooseEvent
import com.coffeeit.coffeemachine.ui.base.BaseViewHolder

class OverviewViewHolder(itemView: View) : BaseViewHolder(itemView) {
    // For OverviewViewHolder, this function will not be used
    override fun createChooseEvent(data: SelectionDataType): ChooseEvent {
        return ChooseEvent.ChooseType(data, itemView)
    }

    override fun wrapperRadioButton(radioButton: RadioButton) {
        radioButton.isChecked = true
        radioButton.isClickable = false
    }

    override fun notifyChosenEvent(data: SelectionDataType) {
    }
}
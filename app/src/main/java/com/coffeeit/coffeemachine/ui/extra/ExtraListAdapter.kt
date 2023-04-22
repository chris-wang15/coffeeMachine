package com.coffeeit.coffeemachine.ui.extra

import android.view.LayoutInflater
import android.view.ViewGroup
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.ui.base.BaseListAdapter
import com.coffeeit.coffeemachine.ui.base.BaseViewHolder

class ExtraListAdapter(list: List<SelectionDataType>) : BaseListAdapter(list) {
    override fun shouldShowEdit() = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ExtraViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.selection_item_container,
                parent,
                false
            )
        )
    }
}

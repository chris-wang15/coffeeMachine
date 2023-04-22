package com.coffeeit.coffeemachine.ui.size

import android.view.LayoutInflater
import android.view.ViewGroup
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.modle.data.SelectionDataType
import com.coffeeit.coffeemachine.ui.base.BaseListAdapter
import com.coffeeit.coffeemachine.ui.base.BaseViewHolder

class SizeListAdapter(list: List<SelectionDataType>) : BaseListAdapter(list) {
    override fun shouldShowEdit() = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return SizeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.selection_item_container,
                parent,
                false
            )
        )
    }
}

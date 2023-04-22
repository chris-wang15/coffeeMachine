package com.coffeeit.coffeemachine.ui.type

import android.view.LayoutInflater
import android.view.ViewGroup
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.ui.base.BaseListAdapter
import com.coffeeit.coffeemachine.ui.base.BaseViewHolder

class TypeListAdapter : BaseListAdapter(emptyList()) {
    override fun shouldShowEdit() = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TypeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.selection_item_container,
                parent,
                false
            )
        )
    }
}

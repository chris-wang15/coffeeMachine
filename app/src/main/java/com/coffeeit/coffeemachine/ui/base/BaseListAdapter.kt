package com.coffeeit.coffeemachine.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.coffeeit.coffeemachine.modle.data.SelectionDataType

abstract class BaseListAdapter(list: List<SelectionDataType>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private val dataList: MutableList<SelectionDataType> = list.toMutableList()

    abstract fun shouldShowEdit(): Boolean

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int
    ) {
        val data = dataList[position]
        holder.bind(data, shouldShowEdit())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<SelectionDataType>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}
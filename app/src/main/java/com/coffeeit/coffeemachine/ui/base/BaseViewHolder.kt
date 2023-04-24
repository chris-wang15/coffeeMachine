package com.coffeeit.coffeemachine.ui.base

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.modle.data.*
import com.coffeeit.coffeemachine.modle.event.ChooseEvent
import com.coffeeit.coffeemachine.modle.event.NavigateEvent
import com.coffeeit.coffeemachine.utils.EventBus
import com.coffeeit.coffeemachine.utils.dip2px
import com.coffeeit.coffeemachine.utils.onClick
import com.github.satoshun.coroutine.autodispose.view.autoDisposeScope
import kotlinx.coroutines.launch

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var detailTitle: TextView = itemView.findViewById(R.id.item_title)
    private var editButton: TextView = itemView.findViewById(R.id.item_edit)
    private var itemImg: ImageView = itemView.findViewById(R.id.item_img)
    private var separator: View = itemView.findViewById(R.id.item_seperate)
    private var subSelections: RadioGroup = itemView.findViewById(R.id.sub_radio_group)

    // TODO use viewmodel and saveInstanceState to store the expand state
    private var isExpanded = false

    abstract fun createChooseEvent(data: SelectionDataType): ChooseEvent

    open fun wrapperRadioButton(radioButton: RadioButton) {}

    open fun notifyChosenEvent(data: SelectionDataType) {
        itemView.autoDisposeScope.launch {
            val event = createChooseEvent(data)
            EventBus.publish(event)
        }
    }

    fun bind(data: SelectionDataType, showEdit: Boolean) {
        detailTitle.text = data.getName()
        itemImg.setImageResource(provideResId(data.getName()))
        if (showEdit) {
            editButton.visibility = View.VISIBLE
            editButton.onClick {
                it ?: return@onClick
                itemView.autoDisposeScope.launch {
                    val event = when (data) {
                        is TypeData -> NavigateEvent.ToTypePage(it)
                        is SizeData -> NavigateEvent.ToSizePage(it)
                        is ExtraData -> NavigateEvent.ToExtraPage(it)
                        else -> null
                    }
                    if (event != null) {
                        EventBus.publish(event)
                    }
                }
            }
        } else {
            editButton.visibility = View.GONE
        }
        if (data.getSub().isNotEmpty()) {
            initSubSelections(data, itemImg.context)
            expand()
            itemView.onClick {
                if (isExpanded) retract() else expand()
            }
        } else {
            retract()
            itemView.onClick {
                notifyChosenEvent(data)
            }
        }
    }

    private fun initSubSelections(data: SelectionDataType, context: Context) {
        data.getSub().forEach { tmpSubData ->
            val radioButton = RadioButton(context).apply {
                text = tmpSubData.getName()
                setTextColor(Color.WHITE)
                textSize = 14f
                setBackgroundColor(Color.parseColor("#9BC88B"))
            }
            val lp = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                context.dip2px(24f),
            ).apply {
                setMargins(0, 0, 0, context.dip2px(16f))
                gravity = Gravity.CENTER_VERTICAL and Gravity.RIGHT
                layoutDirection = View.LAYOUT_DIRECTION_RTL
            }
            radioButton.onClick {
                val eventData = ExtraData(
                    data.getId(),
                    data.getName(),
                    arrayListOf(SubData(tmpSubData.getId(), tmpSubData.getName()))
                )
                notifyChosenEvent(eventData)
            }
            wrapperRadioButton(radioButton)
            subSelections.addView(radioButton, lp)
        }
    }

    private fun expand() {
        isExpanded = true
        separator.visibility = View.VISIBLE
        subSelections.visibility = View.VISIBLE
    }

    private fun retract() {
        isExpanded = false
        separator.visibility = View.GONE
        subSelections.visibility = View.GONE
    }
}
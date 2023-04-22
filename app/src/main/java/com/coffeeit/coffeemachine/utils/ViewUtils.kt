package com.coffeeit.coffeemachine.utils

import android.content.Context
import android.view.View

fun View.onClick(clickHandler: (View?) -> Unit) {
    this.setOnClickListener(
        object : DuplicateClickListener() {
            override fun onSaveCLick(v: View?) {
                clickHandler(v)
            }
        }
    )
}

fun Context.dip2px(dp: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}
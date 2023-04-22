package com.coffeeit.coffeemachine.ui.base

import com.coffeeit.coffeemachine.R

// Note we should upload pics to the cdn, this is a bad way to use pics
fun provideResId(name: String): Int {
    return when(name) {
        "Ristretto" -> R.drawable.coffee0
        "Cappuccino" -> R.drawable.coffee1
        "Espresso" -> R.drawable.coffee2
        "Venti" -> R.drawable.size_small
        "Tall" -> R.drawable.size_medium
        "Large" -> R.drawable.size_large
        "Select the amount of sugar" -> R.drawable.extra_sugar
        "Select type of milk" -> R.drawable.extra_milk
        else -> R.drawable.coffee0
    }
}
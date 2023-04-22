package com.coffeeit.coffeemachine.modle.event

import android.view.View
import com.coffeeit.coffeemachine.modle.data.SelectionDataType

/**
 * Represent that the user has chosen some type or size or extra info
 */
sealed class ChooseEvent(val data: SelectionDataType, val v : View) {
    class ChooseType(data: SelectionDataType, v : View) : ChooseEvent(data, v)
    class ChooseSize(data: SelectionDataType, v : View) : ChooseEvent(data, v)
    class ChooseExtra(data: SelectionDataType, v : View) : ChooseEvent(data, v)
}

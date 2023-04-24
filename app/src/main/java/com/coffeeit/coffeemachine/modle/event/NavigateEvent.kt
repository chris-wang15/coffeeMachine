package com.coffeeit.coffeemachine.modle.event

import android.view.View
import com.coffeeit.coffeemachine.modle.data.SelectionDataType

/**
 * Represent that the user has chosen some type or size or extra info
 */
sealed class NavigateEvent(val v : View) {
    class ToTypePage(v : View) : NavigateEvent(v)
    class ToSizePage(v : View) : NavigateEvent(v)
    class ToExtraPage(v : View) : NavigateEvent(v)
}

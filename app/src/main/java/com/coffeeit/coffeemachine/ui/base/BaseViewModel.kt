package com.coffeeit.coffeemachine.ui.base

import android.view.View
import androidx.lifecycle.ViewModel
import com.coffeeit.coffeemachine.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    val mainRepository = MainRepository

    open val noResultViewVisibility: StateFlow<Int> = MutableStateFlow(View.GONE).asStateFlow()

    open val recyclerVisibility: StateFlow<Int> = MutableStateFlow(View.GONE).asStateFlow()

    open val loadingViewVisibility: StateFlow<Int> = MutableStateFlow(View.GONE).asStateFlow()

    open val brewButtonVisibility: StateFlow<Int> = MutableStateFlow(View.GONE).asStateFlow()

    companion object {
        const val COFFEE_ORDER = "COFFEE_ORDER"
    }
}
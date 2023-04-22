package com.coffeeit.coffeemachine.ui.type

import android.view.View
import androidx.lifecycle.viewModelScope
import com.coffeeit.coffeemachine.modle.CoffeeMachine
import com.coffeeit.coffeemachine.modle.state.DataState
import com.coffeeit.coffeemachine.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class TypePageViewModel(state: StateFlow<DataState<CoffeeMachine>>) : BaseViewModel() {

    override val noResultViewVisibility: StateFlow<Int> = state.mapLatest {
        when (it) {
            is DataState.Error -> View.VISIBLE
            else -> View.GONE
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = View.GONE
    )

    override val recyclerVisibility: StateFlow<Int> = state.mapLatest {
        when (it) {
            is DataState.Success -> View.VISIBLE
            else -> View.GONE
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = View.GONE
    )

    override val loadingViewVisibility: StateFlow<Int> = state.mapLatest {
        when (it) {
            is DataState.Rest -> View.VISIBLE
            is DataState.Loading -> View.VISIBLE
            else -> View.GONE
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = View.GONE
    )

    override val brewButtonVisibility: StateFlow<Int> = MutableStateFlow(View.GONE).asStateFlow()
}
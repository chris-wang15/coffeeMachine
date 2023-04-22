package com.coffeeit.coffeemachine.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coffeeit.coffeemachine.MainApp
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.modle.state.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class MainPageViewModel(state: StateFlow<DataState<String>>) : ViewModel() {

    val titleText: StateFlow<String> = state.mapLatest {
        when (it) {
            is DataState.Rest -> MainApp.App.getString(R.string.machine_id_des_rest)
            is DataState.Success -> MainApp.App.getString(R.string.machine_id_des_success)
            is DataState.Loading -> MainApp.App.getString(R.string.machine_id_des_loading)
            is DataState.Error -> MainApp.App.getString(R.string.machine_id_des_error)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = MainApp.App.getString(R.string.machine_id_des_rest)
    )

    companion object {
        private const val TAG = "MainPageViewModel"
    }
}
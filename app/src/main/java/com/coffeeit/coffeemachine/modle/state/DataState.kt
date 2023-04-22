package com.coffeeit.coffeemachine.modle.state

sealed class DataState<out R> {
    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val exception: java.lang.Exception): DataState<Nothing>()
    object Loading: DataState<Nothing>()
    object Rest: DataState<Nothing>()
}

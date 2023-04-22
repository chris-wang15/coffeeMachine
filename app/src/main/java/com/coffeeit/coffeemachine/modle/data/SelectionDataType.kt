package com.coffeeit.coffeemachine.modle.data

interface SelectionDataType {
    fun getId(): String
    fun getName(): String
    fun getSub(): List<SelectionDataType>
}
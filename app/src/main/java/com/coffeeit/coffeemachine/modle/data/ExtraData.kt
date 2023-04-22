package com.coffeeit.coffeemachine.modle.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExtraData(
    @SerializedName("_id")
    val e_id: String = "",
    @SerializedName("name")
    val e_name: String = "",
    @SerializedName("subselections")
    val subSelectionData: List<SubData> = emptyList()
) : Serializable, SelectionDataType {
    companion object {
        private const val serialVersionUID = -63L
    }

    override fun getId() = e_id

    override fun getName() = e_name

    override fun getSub() = subSelectionData
}
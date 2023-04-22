package com.coffeeit.coffeemachine.modle.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TypeData(
    @SerializedName("_id")
    val t_id: String = "",
    @SerializedName("name")
    val t_name: String = "",
    @SerializedName("sizes")
    val sizes: List<String> = emptyList(),
    @SerializedName("extras")
    val extras: List<String> = emptyList(),
) : Serializable, SelectionDataType {
    companion object {
        private const val serialVersionUID = -10959L
    }

    override fun getId() = t_id

    override fun getName() = t_name

    override fun getSub() = emptyList<SelectionDataType>()
}
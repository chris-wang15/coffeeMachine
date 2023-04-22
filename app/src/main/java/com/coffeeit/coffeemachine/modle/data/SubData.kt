package com.coffeeit.coffeemachine.modle.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubData(
    @SerializedName("_id")
    val s_id: String = "",
    @SerializedName("name")
    val s_name: String = ""
) : Serializable, SelectionDataType {
    companion object {
        private const val serialVersionUID = -8L
    }

    override fun getId() = s_id

    override fun getName() = s_name

    override fun getSub() = emptyList<SelectionDataType>()
}
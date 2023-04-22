package com.coffeeit.coffeemachine.modle.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SizeData(
    @SerializedName("_id")
    val s_id: String = "",
    @SerializedName("name")
    val s_name: String = ""
) : Serializable, SelectionDataType {
    companion object {
        private const val serialVersionUID = -59L
    }

    override fun getId() = s_id

    override fun getName() = s_name

    override fun getSub() = emptyList<SelectionDataType>()
}
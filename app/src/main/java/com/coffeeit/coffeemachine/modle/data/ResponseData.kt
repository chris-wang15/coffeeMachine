package com.coffeeit.coffeemachine.modle.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseData(
    // state code like 200 success, 500 machine unavailable
    @SerializedName("state_code")
    val state: Int = 0,
    @SerializedName("_id")
    val machineId: String = "",
    @SerializedName("types")
    val types: List<TypeData> = emptyList(),
    @SerializedName("sizes")
    val sizes: List<SizeData> = emptyList(),
    @SerializedName("extras")
    val extras: List<ExtraData> = emptyList(),
) : Serializable {
    companion object {
        private const val serialVersionUID = -96494002954378L
    }
}
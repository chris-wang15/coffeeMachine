package com.coffeeit.coffeemachine.modle

import android.util.ArrayMap
import com.coffeeit.coffeemachine.modle.data.ExtraData
import com.coffeeit.coffeemachine.modle.data.ResponseData
import com.coffeeit.coffeemachine.modle.data.SizeData
import com.coffeeit.coffeemachine.modle.data.TypeData

/**
 * Represent all choices of one coffee machine
 */
class CoffeeMachine private constructor(
    val types: List<TypeData>,
    sizes: List<SizeData>,
    extras: List<ExtraData>
) {

    companion object {
        fun create(data: ResponseData): CoffeeMachine {
            return CoffeeMachine(data.types, data.sizes, data.extras)
        }
    }

    private val _types: ArrayMap<String, TypeData> = ArrayMap()
    val typeMap: Map<String, TypeData> = _types

    private val _sizeMap: ArrayMap<String, SizeData> = ArrayMap()
    val sizeMap: Map<String, SizeData> = _sizeMap

    private val _extraMap: ArrayMap<String, ExtraData> = ArrayMap()
    val extraMap: Map<String, ExtraData> = _extraMap

    init {
        types.forEach {
            _types[it.t_id] = it
        }
        sizes.forEach {
            _sizeMap[it.s_id] = it
        }
        extras.forEach {
            _extraMap[it.e_id] = it
        }
    }
}
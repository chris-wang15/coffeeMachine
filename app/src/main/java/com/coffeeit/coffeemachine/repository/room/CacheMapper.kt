package com.coffeeit.coffeemachine.repository.room

import com.coffeeit.coffeemachine.modle.CoffeeOrder

class CacheMapper {
    fun mapFromEntity(it: CacheEntity): CoffeeOrder {
        return CoffeeOrder(
            machineId = it.machineId,
        ).apply {
            typeId = it.typeId
            typeName = it.typeName
            sizeId = it.sizeId
            sizeName = it.sizeName
            extras = it.extras
        }
    }

    fun mapToEntity(it: CoffeeOrder): CacheEntity {
        return CacheEntity(
            id = 0,
            machineId = it.machineId,
            typeId = it.typeId,
            typeName = it.typeName,
            sizeId = it.sizeId,
            sizeName = it.sizeName,
            extras = it.extras,
        )
    }

    fun mapFromEntityList(entities: List<CacheEntity>): List<CoffeeOrder> {
        return entities.map { mapFromEntity(it) }
    }
}

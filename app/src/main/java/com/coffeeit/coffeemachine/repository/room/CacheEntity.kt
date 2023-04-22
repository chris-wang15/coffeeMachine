package com.coffeeit.coffeemachine.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
data class CacheEntity(

    @PrimaryKey(autoGenerate = true) // TODO
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "machineId")
    var machineId: String = "",

    @ColumnInfo(name = "typeId")
    var typeId: String = "",

    @ColumnInfo(name = "typeName")
    var typeName: String = "",

    @ColumnInfo(name = "sizeId")
    var sizeId: String = "",

    @ColumnInfo(name = "sizeName")
    var sizeName: String = "",
)
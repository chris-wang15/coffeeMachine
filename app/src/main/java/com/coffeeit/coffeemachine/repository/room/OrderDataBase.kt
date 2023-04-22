package com.coffeeit.coffeemachine.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CacheEntity::class], version = 1)
abstract class OrderDataBase : RoomDatabase() {

    abstract fun getDao(): OrderListDao

    companion object {
        const val DATA_BASE_NAME = "order_history_db"
    }
}
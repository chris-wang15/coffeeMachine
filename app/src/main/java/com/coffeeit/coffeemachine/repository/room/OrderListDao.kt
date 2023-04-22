package com.coffeeit.coffeemachine.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cacheEntity: CacheEntity)

    // TODO use flow
    @Query("SELECT * FROM order_table ORDER BY id DESC")
    suspend fun getOrderList(): List<CacheEntity>

}
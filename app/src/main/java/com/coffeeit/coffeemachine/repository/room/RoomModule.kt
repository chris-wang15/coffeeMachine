package com.coffeeit.coffeemachine.repository.room

import android.app.Application
import androidx.room.Room

object RoomModule {

    lateinit var dao: OrderListDao

    fun init(context: Application) {
        dao = Room.databaseBuilder(
            context,
            OrderDataBase::class.java,
            OrderDataBase.DATA_BASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
            .getDao()
    }
}
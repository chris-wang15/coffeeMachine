package com.coffeeit.coffeemachine

import android.app.Application
import com.coffeeit.coffeemachine.repository.room.RoomModule

class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()
        App = this
        RoomModule.init(this)
    }

    companion object {
        lateinit var App : Application
    }
}
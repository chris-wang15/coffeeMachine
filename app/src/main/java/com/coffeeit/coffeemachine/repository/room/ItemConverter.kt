package com.coffeeit.coffeemachine.repository.room

import androidx.room.TypeConverter
import com.coffeeit.coffeemachine.modle.CoffeeExtraInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemConverter {
    @TypeConverter
    fun stringToObject(value: String): List<CoffeeExtraInfo> {
        val listType = object : TypeToken<List<CoffeeExtraInfo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<CoffeeExtraInfo>): String {
        return Gson().toJson(list)
    }
}
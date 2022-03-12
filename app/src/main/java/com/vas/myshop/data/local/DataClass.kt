package com.vas.myshop.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vas.feature_cart_screen.data.network.model.BasketModelApi

object DataClass {
    @TypeConverter
    fun restoreList(listOfString: String?): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveListOfString(listOfString: List<String?>?): String {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun restoreListCart(listOfString: String?): List<BasketModelApi> {
        return Gson().fromJson(listOfString, object : TypeToken<List<BasketModelApi?>?>() {}.type)
    }

    @TypeConverter
    fun saveListOfStringCart(listOfString: List<BasketModelApi?>?): String {
        return Gson().toJson(listOfString)
    }
}
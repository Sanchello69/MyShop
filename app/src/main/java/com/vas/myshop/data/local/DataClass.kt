package com.vas.myshop.data.local

import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vas.feature_cart_screen.data.network.model.BasketModelApi
import com.vas.feature_main_screen.data.network.model.BestSellerApi
import com.vas.feature_main_screen.data.network.model.HomeStoreApi

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

    @TypeConverter
    fun restoreListHomeStore(listOfString: String?): List<HomeStoreApi> {
        return Gson().fromJson(listOfString, object : TypeToken<List<HomeStoreApi?>?>() {}.type)
    }

    @TypeConverter
    fun saveListOfStringHomeStore(listOfString: List<HomeStoreApi?>?): String {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun restoreListBestSeller(listOfString: String?): List<BestSellerApi> {
        return Gson().fromJson(listOfString, object : TypeToken<List<BestSellerApi?>?>() {}.type)
    }

    @TypeConverter
    fun saveListOfStringBestSeller(listOfString: List<BestSellerApi?>?): String {
        return Gson().toJson(listOfString)
    }
}
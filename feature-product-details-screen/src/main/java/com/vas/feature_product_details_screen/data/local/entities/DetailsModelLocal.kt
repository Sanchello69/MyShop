package com.vas.feature_product_details_screen.data.local.entities

import androidx.room.*
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




@Entity
data class DetailsModelLocal(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val cpu: String,

    val camera: String,

    @ColumnInfo(name = "is_favorites")
    val isFavorites: Boolean,

    val price: Int,

    val rating: Int,

    val sd: String,

    val ssd: String,

    val title: String,

    //@TypeConverters(StringListToGsonConverter.class)
    val images: List<String>,

    //@TypeConverters(StringListToGsonConverter.class)
    val color: List<String>,

    //@TypeConverters(StringListToGsonConverter.class)
    val capacity: List<String>
)

object StringListToGsonConverter {
    @TypeConverter
    fun restoreList(listOfString: String?): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveListOfString(listOfString: List<String?>?): String {
        return Gson().toJson(listOfString)
    }
}

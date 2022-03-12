package com.vas.feature_product_details_screen.data.local.entity

import androidx.room.*

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

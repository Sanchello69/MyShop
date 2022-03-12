package com.vas.feature_main_screen.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vas.feature_main_screen.data.network.model.BestSellerApi
import com.vas.feature_main_screen.data.network.model.HomeStoreApi

@Entity
data class MainModelLocal(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "list_home_store")
    val listHomeStore: List<HomeStoreApi>,

    @ColumnInfo(name = "list_best_seller")
    val listBestSeller: List<BestSellerApi>

)

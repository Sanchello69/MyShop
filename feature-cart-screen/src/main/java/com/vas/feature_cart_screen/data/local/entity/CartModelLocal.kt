package com.vas.feature_cart_screen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vas.feature_cart_screen.data.network.model.BasketModelApi

@Entity
data class CartModelLocal(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val delivery: String,

    val total: Int,

    val basket: List<BasketModelApi>

)

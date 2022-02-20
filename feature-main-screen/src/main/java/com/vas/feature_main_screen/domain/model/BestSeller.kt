package com.vas.feature_main_screen.domain.model

data class BestSeller(

    val id: Int,

    val isFavorites: Boolean,

    val title: String,

    val price: Int,

    val priceDiscount: Int,

    val picture: String

    )

package com.vas.feature_cart_screen.data.network.model

data class CartModelApi(

    val delivery: String,

    val total: Int,

    val basket: List<BasketModelApi>

)

package com.vas.feature_cart_screen.domain.model

data class CartModel(

    val delivery: String,

    val total: Int,

    val basket: List<BasketModel>

)

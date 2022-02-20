package com.vas.feature_main_screen.data.network.model

import com.google.gson.annotations.SerializedName

data class BestSellerApi(

    @SerializedName("id")
    val id: Int,

    @SerializedName("is_favorites")
    val isFavorites: Boolean,

    @SerializedName("title")
    val title: String,

    @SerializedName("price_without_discount")
    val price: Int,

    @SerializedName("discount_price")
    val priceDiscount: Int,

    @SerializedName("picture")
    val picture: String

)

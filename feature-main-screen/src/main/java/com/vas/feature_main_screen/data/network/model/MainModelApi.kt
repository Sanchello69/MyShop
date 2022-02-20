package com.vas.feature_main_screen.data.network.model

import com.google.gson.annotations.SerializedName

data class MainModelApi(

    @SerializedName("home_store")
    val listHomeStore: List<HomeStoreApi>,

    @SerializedName("best_seller")
    val listBestSeller: List<BestSellerApi>

)

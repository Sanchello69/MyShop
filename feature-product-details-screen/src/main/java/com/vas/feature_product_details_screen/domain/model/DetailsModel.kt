package com.vas.feature_product_details_screen.domain.model

data class DetailsModel(

    val cpu: String,

    val camera: String,

    val isFavorites: Boolean,

    val price: Int,

    val rating: Int,

    val sd: String,

    val ssd: String,

    val title: String,

    val images: List<String>,

    val color: List<String>,

    val capacity: String

)

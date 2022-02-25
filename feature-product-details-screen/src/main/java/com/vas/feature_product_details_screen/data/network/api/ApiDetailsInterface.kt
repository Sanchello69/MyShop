package com.vas.feature_product_details_screen.data.network.api

import com.vas.feature_product_details_screen.data.network.model.DetailsModelApi
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiDetailsInterface {
    @Headers("x-apikey: 61ddae2e95cb716ea5ee48e4")
    @GET("detail")
    suspend fun getDetailsResult(): List<DetailsModelApi>
}
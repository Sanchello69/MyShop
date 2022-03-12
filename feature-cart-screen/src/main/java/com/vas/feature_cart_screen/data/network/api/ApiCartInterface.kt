package com.vas.feature_cart_screen.data.network.api

import com.vas.feature_cart_screen.data.network.model.CartModelApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiCartInterface {
    @Headers("x-apikey: 61ddae2e95cb716ea5ee48e4")
    @GET("cart")
    suspend fun getCartResult(): Response<List<CartModelApi>>
}
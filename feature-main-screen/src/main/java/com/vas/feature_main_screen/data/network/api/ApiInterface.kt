package com.vas.feature_main_screen.data.network.api

import com.vas.feature_main_screen.data.network.model.MainModelApi
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("x-apikey: 61ddae2e95cb716ea5ee48e4")
    @GET("home")
    suspend fun getMainResult(): List<MainModelApi>
}
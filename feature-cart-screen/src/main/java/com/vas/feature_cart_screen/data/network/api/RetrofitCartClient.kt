package com.vas.feature_cart_screen.data.network.api

import com.vas.core.data.network.api.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCartClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiCartInterface = getRetrofit().create(ApiCartInterface::class.java)

}
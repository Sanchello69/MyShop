package com.vas.feature_main_screen.data.network.api

import com.vas.core.data.network.api.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiInterface = getRetrofit().create(ApiInterface::class.java)

}
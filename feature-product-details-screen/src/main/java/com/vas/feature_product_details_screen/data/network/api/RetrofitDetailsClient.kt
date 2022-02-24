package com.vas.feature_product_details_screen.data.network.api

import com.vas.core.data.network.api.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDetailsClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiDetailsInterface = getRetrofit().create(ApiDetailsInterface::class.java)

}
package com.vas.feature_main_screen.data.network.api

class ApiHelper (private val apiInterface: ApiInterface) {
    suspend fun getMainResult() =
        apiInterface.getMainResult()
}
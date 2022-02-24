package com.vas.feature_product_details_screen.data.network.api

class ApiDetailsHelper(private val apiInterface: ApiDetailsInterface) {
    suspend fun getDetailsResult() =
        apiInterface.getDetailsResult()
}
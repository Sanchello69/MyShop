package com.vas.feature_cart_screen.data.network.api

class ApiCartHelper(private val apiInterface: ApiCartInterface) {
    suspend fun getCartResult() =
        apiInterface.getCartResult()
}
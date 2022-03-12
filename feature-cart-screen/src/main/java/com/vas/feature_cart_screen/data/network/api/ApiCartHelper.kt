package com.vas.feature_cart_screen.data.network.api

import com.vas.core.utils.BaseDataSource

class ApiCartHelper(private val apiInterface: ApiCartInterface) : BaseDataSource() {
    suspend fun getCartResult() =
        getResult { apiInterface.getCartResult() }
}
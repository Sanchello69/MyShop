package com.vas.feature_product_details_screen.data.network.api

import com.vas.core.utils.BaseDataSource

class ApiDetailsHelper(private val apiInterface: ApiDetailsInterface) : BaseDataSource() {
    suspend fun getDetailsResult() =
        getResult { apiInterface.getDetailsResult() }
}
package com.vas.feature_main_screen.data.network.api

import com.vas.core.utils.BaseDataSource

class ApiHelper (private val apiInterface: ApiInterface) : BaseDataSource(){
    suspend fun getMainResult() =
        getResult { apiInterface.getMainResult() }

}
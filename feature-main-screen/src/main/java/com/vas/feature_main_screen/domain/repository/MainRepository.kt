package com.vas.feature_main_screen.domain.repository

import com.vas.feature_main_screen.domain.model.MainModel

interface MainRepository  {

    suspend fun getMainResult() : MainModel

}
package com.vas.myshop.di

import com.vas.feature_main_screen.data.network.api.ApiHelper
import com.vas.feature_main_screen.data.network.api.RetrofitClient
import com.vas.feature_main_screen.data.repository.MainRepositoryImpl
import com.vas.feature_main_screen.domain.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideApiHelper(): ApiHelper{
        return ApiHelper(RetrofitClient.apiInterface)
    }

    @Provides
    fun provideMainRepository(apiHelper: ApiHelper): MainRepository{
        return MainRepositoryImpl(apiHelper = apiHelper)
    }

}
package com.vas.myshop.di

import com.vas.feature_main_screen.data.network.api.ApiHelper
import com.vas.feature_main_screen.data.network.api.RetrofitClient
import com.vas.feature_main_screen.data.repository.MainRepositoryImpl
import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.feature_product_details_screen.data.network.api.ApiDetailsHelper
import com.vas.feature_product_details_screen.data.network.api.RetrofitDetailsClient
import com.vas.feature_product_details_screen.data.repository.DetailsRepositoryImpl
import com.vas.feature_product_details_screen.domain.repository.DetailsRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    //main
    @Provides
    fun provideApiHelper(): ApiHelper{
        return ApiHelper(RetrofitClient.apiInterface)
    }

    @Provides
    fun provideMainRepository(apiHelper: ApiHelper): MainRepository{
        return MainRepositoryImpl(apiHelper = apiHelper)
    }


    //details
    @Provides
    fun provideDetailsApiHelper(): ApiDetailsHelper{
        return ApiDetailsHelper(RetrofitDetailsClient.apiInterface)
    }

    @Provides
    fun provideDetailsRepository(apiHelper: ApiDetailsHelper): DetailsRepository{
        return DetailsRepositoryImpl(apiHelper = apiHelper)
    }

}
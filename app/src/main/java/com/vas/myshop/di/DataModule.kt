package com.vas.myshop.di

import android.content.Context
import com.vas.feature_cart_screen.data.network.api.ApiCartHelper
import com.vas.feature_cart_screen.data.network.api.RetrofitCartClient
import com.vas.feature_cart_screen.data.repository.CartRepositoryImpl
import com.vas.feature_cart_screen.domain.repository.CartRepository
import com.vas.feature_main_screen.data.network.api.ApiHelper
import com.vas.feature_main_screen.data.network.api.RetrofitClient
import com.vas.feature_main_screen.data.repository.MainRepositoryImpl
import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.feature_product_details_screen.data.local.DetailsDatabase
import com.vas.feature_product_details_screen.data.local.dao.DetailsDao
import com.vas.feature_product_details_screen.data.network.api.ApiDetailsHelper
import com.vas.feature_product_details_screen.data.network.api.RetrofitDetailsClient
import com.vas.feature_product_details_screen.data.repository.DetailsRepositoryImpl
import com.vas.feature_product_details_screen.domain.repository.DetailsRepository
import com.vas.feature_product_details_screen.presentation.DetailsFragment
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
    fun provideDetailsDao(context: Context): DetailsDao{
        return DetailsDatabase.getDatabase(context).getMainDao()
    }

    @Provides
    fun provideDetailsRepository(dao: DetailsDao, apiHelper: ApiDetailsHelper): DetailsRepositoryImpl{
        return DetailsRepositoryImpl(dao = dao,apiHelper = apiHelper)
    }


    //cart
    @Provides
    fun provideCartApiHelper(): ApiCartHelper{
        return ApiCartHelper(RetrofitCartClient.apiInterface)
    }

    @Provides
    fun provideCartRepository(apiHelper: ApiCartHelper): CartRepository{
        return CartRepositoryImpl(apiHelper = apiHelper)
    }
}
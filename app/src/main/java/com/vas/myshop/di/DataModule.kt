package com.vas.myshop.di

import android.content.Context
import com.vas.feature_cart_screen.data.local.dao.CartDao
import com.vas.feature_cart_screen.data.network.api.ApiCartHelper
import com.vas.feature_cart_screen.data.network.api.RetrofitCartClient
import com.vas.feature_cart_screen.data.repository.CartRepositoryImpl
import com.vas.feature_cart_screen.domain.repository.CartRepository
import com.vas.feature_main_screen.data.local.dao.MainDao
import com.vas.feature_main_screen.data.network.api.ApiHelper
import com.vas.feature_main_screen.data.network.api.RetrofitClient
import com.vas.feature_main_screen.data.repository.MainRepositoryImpl
import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.myshop.data.local.LocalDatabase
import com.vas.feature_product_details_screen.data.local.dao.DetailsDao
import com.vas.feature_product_details_screen.data.network.api.ApiDetailsHelper
import com.vas.feature_product_details_screen.data.network.api.RetrofitDetailsClient
import com.vas.feature_product_details_screen.data.repository.DetailsRepositoryImpl
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
    fun provideDao(context: Context): MainDao{
        return LocalDatabase.getDatabase(context).getMainDao()
    }

    @Provides
    fun provideMainRepository(dao: MainDao, apiHelper: ApiHelper): MainRepository{
        return MainRepositoryImpl(dao = dao, apiHelper = apiHelper)
    }


    //details

    @Provides
    fun provideDetailsApiHelper(): ApiDetailsHelper{
        return ApiDetailsHelper(RetrofitDetailsClient.apiInterface)
    }

    @Provides
    fun provideDetailsDao(context: Context): DetailsDao{
        return LocalDatabase.getDatabase(context).getDetailsDao()
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
    fun provideCartDao(context: Context): CartDao{
        return LocalDatabase.getDatabase(context).getCartDao()
    }

    @Provides
    fun provideCartRepository(dao: CartDao, apiHelper: ApiCartHelper): CartRepository{
        return CartRepositoryImpl(dao = dao, apiHelper = apiHelper)
    }
}
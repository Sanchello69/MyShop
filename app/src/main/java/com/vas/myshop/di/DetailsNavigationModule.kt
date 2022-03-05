package com.vas.myshop.di

import com.vas.feature_product_details_screen.navigation.DetailsNavCommandProvider
import com.vas.myshop.navigation.DetailsNavCommandProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface DetailsNavigationModule {

    @Binds
    fun bindDetailsNavigator(impl: DetailsNavCommandProviderImpl): DetailsNavCommandProvider

}
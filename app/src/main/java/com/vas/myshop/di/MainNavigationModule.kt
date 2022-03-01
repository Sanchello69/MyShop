package com.vas.myshop.di

import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.myshop.navigation.MainNavCommandProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface MainNavigationModule {

    @Binds
    fun bindMainNavigator(impl: MainNavCommandProviderImpl): MainNavCommandProvider

}
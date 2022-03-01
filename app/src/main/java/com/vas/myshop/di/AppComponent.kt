package com.vas.myshop.di

import com.vas.feature_main_screen.di.MainDeps
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import com.vas.myshop.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, MainModule::class])
interface AppComponent : MainDeps {

    override val mainViewModelFactory: MainViewModelFactory
    override val mainNavCommandProvider: MainNavCommandProvider

    fun inject(mainActivity: MainActivity)

}
package com.vas.myshop.di

import com.vas.feature_main_screen.di.MainDeps
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import com.vas.myshop.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent : MainDeps {

    override val mainViewModelFactory: MainViewModelFactory

    fun inject(mainActivity: MainActivity)
}
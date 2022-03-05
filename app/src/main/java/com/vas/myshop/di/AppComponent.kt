package com.vas.myshop.di

import com.vas.feature_cart_screen.di.CartDeps
import com.vas.feature_cart_screen.presentation.CartViewModelFactory
import com.vas.feature_main_screen.di.MainDeps
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import com.vas.feature_product_details_screen.di.DetailsDeps
import com.vas.feature_product_details_screen.navigation.DetailsNavCommandProvider
import com.vas.feature_product_details_screen.presentation.DetailsViewModelFactory
import com.vas.myshop.MainActivity
import dagger.Component

@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class,
    MainModule::class,
    DetailsModule::class])
interface AppComponent : MainDeps, DetailsDeps, CartDeps {

    override val mainViewModelFactory: MainViewModelFactory
    override val mainNavCommandProvider: MainNavCommandProvider

    override val detailsViewModelFactory: DetailsViewModelFactory
    override val detailsNavCommandProvider: DetailsNavCommandProvider

    override val cartViewModelFactory: CartViewModelFactory

    fun inject(mainActivity: MainActivity)

}
package com.vas.myshop.di

import com.vas.feature_cart_screen.domain.useCase.GetCartUseCase
import com.vas.feature_cart_screen.presentation.CartViewModelFactory
import com.vas.feature_main_screen.domain.useCase.GetMainUseCase
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import com.vas.feature_product_details_screen.domain.useCase.GetDetailsUseCase
import com.vas.feature_product_details_screen.presentation.DetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    //main
    @Provides
    fun provideMainViewModelFactory(getMainUseCase: GetMainUseCase): MainViewModelFactory{
        return MainViewModelFactory(getMainUseCase = getMainUseCase)
    }

    //details
    @Provides
    fun provideDetailsViewModelFactory(getDetailsUseCase: GetDetailsUseCase): DetailsViewModelFactory{
        return DetailsViewModelFactory(getDetailsUseCase = getDetailsUseCase)
    }

    //cart
    @Provides
    fun provideCartViewModelFactory(getCartUseCase: GetCartUseCase): CartViewModelFactory{
        return CartViewModelFactory(getCartUseCase = getCartUseCase)
    }
}
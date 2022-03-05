package com.vas.myshop.di

import com.vas.feature_cart_screen.domain.repository.CartRepository
import com.vas.feature_cart_screen.domain.useCase.GetCartUseCase
import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.feature_main_screen.domain.useCase.GetMainUseCase
import com.vas.feature_product_details_screen.domain.repository.DetailsRepository
import com.vas.feature_product_details_screen.domain.useCase.GetDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    //main
    @Provides
    fun provideGetMainUseCase(mainRepository: MainRepository): GetMainUseCase{
        return GetMainUseCase(mainRepository = mainRepository)
    }

    //details
    @Provides
    fun provideGetDetailsUseCase(detailsRepository: DetailsRepository): GetDetailsUseCase{
        return GetDetailsUseCase(detailsRepository = detailsRepository)
    }

    //cart
    @Provides
    fun provideGetCartUseCase(cartRepository: CartRepository): GetCartUseCase{
        return GetCartUseCase(cartRepository = cartRepository)
    }

}
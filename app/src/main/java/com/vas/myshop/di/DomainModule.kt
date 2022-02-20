package com.vas.myshop.di

import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.feature_main_screen.domain.useCase.GetMainUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetMainUseCase(mainRepository: MainRepository): GetMainUseCase{
        return GetMainUseCase(mainRepository = mainRepository)
    }

}
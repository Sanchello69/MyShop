package com.vas.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.feature_main_screen.domain.useCase.GetMainUseCase

class MainViewModelFactory(val getMainUseCase: GetMainUseCase) : ViewModelProvider.Factory {

    //private val mainRepository by lazy(LazyThreadSafetyMode.NONE) {
    //    MainRepositoryImpl(apiHelper = apiHelper)
    //}

    //private val getMainUseCase by lazy {
    //    GetMainUseCase(mainRepository = mainRepository)
    //}

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                getMainUseCase = getMainUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")    }
}
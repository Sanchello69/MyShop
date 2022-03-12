package com.vas.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import com.vas.feature_main_screen.domain.useCase.GetMainUseCase

class MainViewModel(private val getMainUseCase: GetMainUseCase) : ViewModel() {

    val main = getMainUseCase.execute()

}
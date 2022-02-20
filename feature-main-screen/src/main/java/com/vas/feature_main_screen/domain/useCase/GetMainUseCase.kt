package com.vas.feature_main_screen.domain.useCase

import com.vas.feature_main_screen.domain.model.MainModel
import com.vas.feature_main_screen.domain.repository.MainRepository

class GetMainUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(): MainModel{
        return mainRepository.getMainResult()
    }

}
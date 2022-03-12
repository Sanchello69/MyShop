package com.vas.feature_main_screen.domain.useCase

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_main_screen.domain.model.MainModel
import com.vas.feature_main_screen.domain.repository.MainRepository

class GetMainUseCase(private val mainRepository: MainRepository) {

    fun execute(): LiveData<Result<List<MainModel>>> {
        return mainRepository.getMainResult()
    }

}
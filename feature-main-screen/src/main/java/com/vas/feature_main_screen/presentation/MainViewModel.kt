package com.vas.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vas.feature_main_screen.domain.useCase.GetMainUseCase
import com.vas.core.presentation.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val getMainUseCase: GetMainUseCase) : ViewModel() {

    fun getMain() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getMainUseCase.execute()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
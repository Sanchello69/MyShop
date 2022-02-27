package com.vas.feature_product_details_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vas.core.presentation.utils.Resource
import com.vas.feature_product_details_screen.domain.useCase.GetDetailsUseCase
import kotlinx.coroutines.Dispatchers

class DetailsViewModel(private val getDetailsUseCase: GetDetailsUseCase) : ViewModel() {

    fun getDetails() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getDetailsUseCase.execute()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
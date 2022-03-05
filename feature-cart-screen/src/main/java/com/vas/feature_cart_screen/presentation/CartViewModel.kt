package com.vas.feature_cart_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vas.core.presentation.utils.Resource
import com.vas.feature_cart_screen.domain.useCase.GetCartUseCase
import kotlinx.coroutines.Dispatchers

class CartViewModel(private val getCartUseCase: GetCartUseCase) : ViewModel() {

    fun getCart() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getCartUseCase.execute()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
package com.vas.feature_cart_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.feature_cart_screen.domain.useCase.GetCartUseCase

class CartViewModelFactory(val getCartUseCase: GetCartUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(
                getCartUseCase = getCartUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
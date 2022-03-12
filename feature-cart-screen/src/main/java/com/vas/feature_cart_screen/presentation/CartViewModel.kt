package com.vas.feature_cart_screen.presentation

import androidx.lifecycle.ViewModel
import com.vas.feature_cart_screen.domain.useCase.GetCartUseCase

class CartViewModel(private val getCartUseCase: GetCartUseCase) : ViewModel() {

    val cart = getCartUseCase.execute()

}
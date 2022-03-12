package com.vas.feature_cart_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vas.core.presentation.utils.Resource
import com.vas.feature_cart_screen.domain.useCase.GetCartUseCase
import kotlinx.coroutines.Dispatchers

class CartViewModel(private val getCartUseCase: GetCartUseCase) : ViewModel() {

    val cart = getCartUseCase.execute()

}
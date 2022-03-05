package com.vas.feature_cart_screen.domain.useCase

import com.vas.feature_cart_screen.domain.model.CartModel
import com.vas.feature_cart_screen.domain.repository.CartRepository

class GetCartUseCase(private val cartRepository: CartRepository) {

    suspend fun execute(): CartModel{
        return cartRepository.getCartResult()
    }

}
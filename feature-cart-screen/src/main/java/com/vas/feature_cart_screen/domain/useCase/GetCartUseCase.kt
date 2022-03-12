package com.vas.feature_cart_screen.domain.useCase

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_cart_screen.domain.model.CartModel
import com.vas.feature_cart_screen.domain.repository.CartRepository

class GetCartUseCase(private val cartRepository: CartRepository) {

    fun execute(): LiveData<Result<List<CartModel>>> {
        return cartRepository.getCartResult()
    }

}
package com.vas.feature_cart_screen.domain.repository

import com.vas.feature_cart_screen.domain.model.CartModel

interface CartRepository {

    suspend fun getCartResult() : CartModel

}
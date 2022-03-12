package com.vas.feature_cart_screen.domain.repository

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_cart_screen.domain.model.CartModel

interface CartRepository {

    fun getCartResult() : LiveData<Result<List<CartModel>>>

}
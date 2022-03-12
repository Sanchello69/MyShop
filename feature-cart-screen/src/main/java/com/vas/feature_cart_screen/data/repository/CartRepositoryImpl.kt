package com.vas.feature_cart_screen.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.vas.core.utils.Result
import com.vas.core.utils.resultLiveData
import com.vas.feature_cart_screen.data.local.dao.CartDao
import com.vas.feature_cart_screen.data.local.entity.CartModelLocal
import com.vas.feature_cart_screen.data.network.api.ApiCartHelper
import com.vas.feature_cart_screen.domain.model.BasketModel
import com.vas.feature_cart_screen.domain.model.CartModel
import com.vas.feature_cart_screen.domain.repository.CartRepository

class CartRepositoryImpl(private val dao: CartDao,
                         private val apiHelper: ApiCartHelper) : CartRepository {

    private val cart = resultLiveData(
        databaseQuery = { dao.getCart() },
        networkCall = { apiHelper.getCartResult() },
        saveCallResult = { dao.insertAllCart(it.map {
            CartModelLocal(
                id = 0,
                delivery = it.delivery,
                total = it.total,
                basket = it.basket
            )
        }) }).map{
        Result<List<CartModel>>(
            status = it.status,
            data = it.data?.map {
                CartModel(
                    delivery = it.delivery,
                    total = it.total,
                    basket = it.basket.map {
                        BasketModel(
                            images = it.images,
                            price = it.price,
                            title = it.title
                        )
                    }
                )
            },
            message = it.message
        )
    }

    override fun getCartResult(): LiveData<Result<List<CartModel>>> {
        return cart
    }

}
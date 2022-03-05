package com.vas.feature_cart_screen.data.repository

import com.vas.feature_cart_screen.data.network.api.ApiCartHelper
import com.vas.feature_cart_screen.data.network.model.CartModelApi
import com.vas.feature_cart_screen.domain.model.BasketModel
import com.vas.feature_cart_screen.domain.model.CartModel
import com.vas.feature_cart_screen.domain.repository.CartRepository

class CartRepositoryImpl(private val apiHelper: ApiCartHelper) : CartRepository {

    override suspend fun getCartResult(): CartModel {
        val cartModelApi = apiHelper.getCartResult()

        //возвращаем данные
        return mapToDomain(cartModelApi)
    }

    private fun mapToDomain(listCartModelApi: List<CartModelApi>): CartModel {

        val cartModelApi = listCartModelApi.last()
        val listBasketApi = cartModelApi.basket

        //приводим данные к моделям из domain слоя

        val listBasket = ArrayList<BasketModel>()
        listBasketApi.forEach {
            listBasket.add(
                BasketModel(
                    title = it.title,
                    images = it.images,
                    price = it.price
                )
            )
        }
        return CartModel(
            delivery = cartModelApi.delivery,
            total = cartModelApi.total,
            basket = listBasket
        )
    }

}
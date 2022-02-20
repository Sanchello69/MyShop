package com.vas.feature_main_screen.data.repository

import com.vas.feature_main_screen.data.network.api.ApiHelper
import com.vas.feature_main_screen.data.network.model.MainModelApi
import com.vas.feature_main_screen.domain.model.BestSeller
import com.vas.feature_main_screen.domain.model.HomeStore
import com.vas.feature_main_screen.domain.model.MainModel
import com.vas.feature_main_screen.domain.repository.MainRepository

class MainRepositoryImpl(private val apiHelper: ApiHelper) : MainRepository {
    override suspend fun getMainResult(): MainModel {
        val mainModelApi = apiHelper.getMainResult()

        //возвращаем данные
        return mapToDomain(mainModelApi)
    }

    private fun mapToDomain(listMainModelApi: List<MainModelApi>): MainModel {
        val mainModelApi = listMainModelApi.last()
        val listBestSellerApi = mainModelApi.listBestSeller
        val listHomeStoreApi = mainModelApi.listHomeStore

        //приводим данные к моделям из domain слоя
        val listHomeStore = ArrayList<HomeStore>()
        listHomeStoreApi.forEach {
            listHomeStore.add(
                HomeStore(
                    id = it.id,
                    isNew = it.isNew,
                    title = it.title,
                    subtitle = it.subtitle,
                    picture = it.picture,
                    isBuy = it.isBuy
                )
            )
        }

        val listBestSeller = ArrayList<BestSeller>()
        listBestSellerApi.forEach {
            listBestSeller.add(
                BestSeller(
                    id = it.id,
                    isFavorites = it.isFavorites,
                    title = it.title,
                    price = it.price,
                    priceDiscount = it.priceDiscount,
                    picture = it.picture
                )
            )
        }
        return MainModel(
            listHomeStore = listHomeStore,
            listBestSeller = listBestSeller
        )
    }
}
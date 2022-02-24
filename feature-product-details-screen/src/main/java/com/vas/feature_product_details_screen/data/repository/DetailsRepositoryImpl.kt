package com.vas.feature_product_details_screen.data.repository

import com.vas.feature_product_details_screen.data.network.api.ApiDetailsHelper
import com.vas.feature_product_details_screen.data.network.model.DetailsModelApi
import com.vas.feature_product_details_screen.domain.model.DetailsModel
import com.vas.feature_product_details_screen.domain.repository.DetailsRepository

class DetailsRepositoryImpl(private val apiHelper: ApiDetailsHelper) : DetailsRepository {

    override suspend fun getDetailsResult(): DetailsModel {
        val detailsModelApi = apiHelper.getDetailsResult()

        //возвращаем данные
        return mapToDomain(detailsModelApi)
    }

    private fun mapToDomain(listDetailsModelApi: List<DetailsModelApi>): DetailsModel {

        val detailsModelApi = listDetailsModelApi.last()

        return DetailsModel(
            cpu = detailsModelApi.cpu,
            camera = detailsModelApi.camera,
            isFavorites = detailsModelApi.isFavorites,
            price = detailsModelApi.price,
            rating = detailsModelApi.rating,
            sd = detailsModelApi.sd,
            ssd = detailsModelApi.ssd,
            title = detailsModelApi.title,
            images = detailsModelApi.images,
            color = detailsModelApi.color,
            capacity = detailsModelApi.capacity
        )
    }

}
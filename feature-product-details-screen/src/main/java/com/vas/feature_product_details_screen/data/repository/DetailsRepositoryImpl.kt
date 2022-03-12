package com.vas.feature_product_details_screen.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.vas.feature_product_details_screen.data.local.dao.DetailsDao
import com.vas.feature_product_details_screen.data.local.entity.DetailsModelLocal
import com.vas.feature_product_details_screen.data.network.api.ApiDetailsHelper
import com.vas.feature_product_details_screen.domain.model.DetailsModel
import com.vas.feature_product_details_screen.domain.repository.DetailsRepository
import com.vas.core.utils.Result
import com.vas.core.utils.resultLiveData

class DetailsRepositoryImpl(private val dao: DetailsDao,
                            private val apiHelper: ApiDetailsHelper) : DetailsRepository {

    private val details = resultLiveData(
        databaseQuery = { dao.getDetails() },
        networkCall = { apiHelper.getDetailsResult() },
        saveCallResult = { dao.insertAllDetails(it.map {
            DetailsModelLocal(
                id = 0,
                cpu = it.cpu,
                camera = it.camera,
                isFavorites = it.isFavorites,
                price = it.price,
                rating = it.rating,
                sd = it.sd,
                ssd = it.ssd,
                title = it.title,
                images = it.images,
                color = it.color,
                capacity = it.capacity
            )
        }) }).map{
            Result<List<DetailsModel>>(
                status = it.status,
                data = it.data?.map {
                    DetailsModel(
                        cpu = it.cpu,
                        camera = it.camera,
                        isFavorites = it.isFavorites,
                        price = it.price,
                        rating = it.rating,
                        sd = it.sd,
                        ssd = it.ssd,
                        title = it.title,
                        images = it.images,
                        color = it.color,
                        capacity = it.capacity
                    )
                },
                message = it.message
            )
    }

    override fun getDetailsResult(): LiveData<Result<List<DetailsModel>>> {
        return details
    }

}
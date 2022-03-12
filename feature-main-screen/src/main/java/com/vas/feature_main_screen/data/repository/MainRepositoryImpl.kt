package com.vas.feature_main_screen.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.vas.core.utils.Result
import com.vas.core.utils.resultLiveData
import com.vas.feature_main_screen.data.local.dao.MainDao
import com.vas.feature_main_screen.data.local.entity.MainModelLocal
import com.vas.feature_main_screen.data.network.api.ApiHelper
import com.vas.feature_main_screen.domain.model.BestSeller
import com.vas.feature_main_screen.domain.model.HomeStore
import com.vas.feature_main_screen.domain.model.MainModel
import com.vas.feature_main_screen.domain.repository.MainRepository

class MainRepositoryImpl(private val dao: MainDao, private val apiHelper: ApiHelper) : MainRepository {

    private val main = resultLiveData(
        databaseQuery = { dao.getMain() },
        networkCall = { apiHelper.getMainResult() },
        saveCallResult = { dao.insertAllMain(it.map {
            MainModelLocal(
                id = 0,
                listHomeStore = it.listHomeStore,
                listBestSeller = it.listBestSeller
            )
        }) }).map{
        Result<List<MainModel>>(
            status = it.status,
            data = it.data?.map {
                MainModel(
                    it.listHomeStore.map {
                        HomeStore(
                            id = it.id,
                            isNew = it.isNew,
                            title = it.title,
                            subtitle = it.subtitle,
                            picture = it.picture,
                            isBuy = it.isBuy
                        )
                    },
                    it.listBestSeller.map {
                        BestSeller(
                            id = it.id,
                            isFavorites = it.isFavorites,
                            title = it.title,
                            price = it.price,
                            priceDiscount = it.priceDiscount,
                            picture = it.picture
                        )
                    }
                )
            },
            message = it.message
        )
    }

    override fun getMainResult(): LiveData<Result<List<MainModel>>> {
        return main
    }
}
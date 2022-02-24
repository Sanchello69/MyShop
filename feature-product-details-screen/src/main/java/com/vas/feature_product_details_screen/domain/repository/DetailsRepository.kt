package com.vas.feature_product_details_screen.domain.repository

import com.vas.feature_product_details_screen.domain.model.DetailsModel

interface DetailsRepository {

    suspend fun getDetailsResult() : DetailsModel

}
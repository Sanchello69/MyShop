package com.vas.feature_product_details_screen.domain.repository

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_product_details_screen.domain.model.DetailsModel

interface DetailsRepository {

    fun getDetailsResult() : LiveData<Result<List<DetailsModel>>>

}
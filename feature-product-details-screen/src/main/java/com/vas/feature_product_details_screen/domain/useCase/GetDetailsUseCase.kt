package com.vas.feature_product_details_screen.domain.useCase

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_product_details_screen.domain.model.DetailsModel
import com.vas.feature_product_details_screen.domain.repository.DetailsRepository

class GetDetailsUseCase (private val detailsRepository: DetailsRepository) {

    fun execute(): LiveData<Result<List<DetailsModel>>>{
        return detailsRepository.getDetailsResult()
    }

}
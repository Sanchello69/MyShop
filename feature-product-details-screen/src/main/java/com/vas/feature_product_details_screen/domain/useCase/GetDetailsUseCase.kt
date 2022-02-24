package com.vas.feature_product_details_screen.domain.useCase

import com.vas.feature_product_details_screen.domain.model.DetailsModel
import com.vas.feature_product_details_screen.domain.repository.DetailsRepository

class GetDetailsUseCase(private val detailsRepository: DetailsRepository) {

    suspend fun execute(): DetailsModel{
        return detailsRepository.getDetailsResult()
    }

}
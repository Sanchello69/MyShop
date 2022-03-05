package com.vas.myshop.navigation

import com.vas.feature_product_details_screen.navigation.DetailsNavCommandProvider
import com.vas.myshop.R
import com.vas.navigation.NavCommand
import javax.inject.Inject

class DetailsNavCommandProviderImpl @Inject constructor() : DetailsNavCommandProvider {
    override val toCart: NavCommand =
        NavCommand(R.id.action_detailsFragment_to_myCartFragment)
}
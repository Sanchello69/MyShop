package com.vas.myshop.navigation

import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.myshop.R
import com.vas.navigation.NavCommand
import javax.inject.Inject

class MainNavCommandProviderImpl @Inject constructor() : MainNavCommandProvider {
    override val toDetails: NavCommand =
        NavCommand(R.id.action_mainFragment_to_detailsFragment)

    override val toCart: NavCommand =
        NavCommand(R.id.action_mainFragment_to_myCartFragment)

    override val toMaps: NavCommand =
        NavCommand(R.id.action_mainFragment_to_mapsFragment)
}
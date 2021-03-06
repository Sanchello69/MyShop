package com.vas.feature_main_screen.navigation

import com.vas.navigation.NavCommand

interface MainNavCommandProvider {
    val toDetails: NavCommand
    val toCart: NavCommand
    val toMaps: NavCommand
}
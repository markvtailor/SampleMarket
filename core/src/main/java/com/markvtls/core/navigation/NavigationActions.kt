package com.markvtls.core.navigation

import androidx.navigation.NavController

/**Cross-module navigation.*/
object NavigationActions {

    fun toMainScreen(navController: NavController) {
        navController.navigate(com.markvtls.core.R.id.action_global_mainScreenFragment)
    }

    fun toDetailsScreen(navController: NavController) {
        navController.navigate(com.markvtls.core.R.id.action_global_detailsFragment)
    }

    fun toCart(navController: NavController) {
        navController.navigate(com.markvtls.core.R.id.action_global_cartFragment)
    }
}
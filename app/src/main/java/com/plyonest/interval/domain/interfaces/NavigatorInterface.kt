package com.plyonest.interval.domain.interfaces

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

interface NavigatorInterface {
    fun setNavigationController(navController: NavHostController)
    fun getNavigationController(): NavHostController
    fun navigate(
        route: String,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    )
}
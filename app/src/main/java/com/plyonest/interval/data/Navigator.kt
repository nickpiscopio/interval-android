package com.plyonest.interval.data

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.plyonest.interval.domain.interfaces.NavigatorInterface

class Navigator: NavigatorInterface {
    private lateinit var navController: NavHostController

    override fun setNavigationController(navController: NavHostController) {
        this.navController = navController
    }

    override fun getNavigationController(): NavHostController {
        return this.navController
    }

    override fun navigate(
        route: String,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ) {
        navController.navigate(route, navOptions, navigatorExtras)
    }
}
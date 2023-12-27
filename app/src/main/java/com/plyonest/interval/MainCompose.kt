package com.plyonest.interval

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plyonest.interval.ui.screens.CreateTimer
import com.plyonest.interval.ui.screens.RunTimer
import com.plyonest.interval.ui.screens.SelectTimer

enum class AppScreen(@StringRes val title: Int? = null) {
    TIMER_SELECT(title = R.string.screen_select_timer_title),
    TIMER_CREATE(title = R.string.screen_create_timer_title),
    TIMER_RUN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { currentScreen.title?.let { Text(stringResource(currentScreen.title)) } },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = stringResource(R.string.back_button)
//                    )
//                }
//            }
//        }
    )
}

@Composable
fun MainCompose (
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.TIMER_SELECT.name
    )

    Scaffold(
        topBar = {
            NavBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.TIMER_SELECT.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppScreen.TIMER_SELECT.name) {
                SelectTimer(navController = navController)
            }
            composable(route = AppScreen.TIMER_CREATE.name) {
                CreateTimer(
                    onStartClicked = {
                        navController.navigate(AppScreen.TIMER_RUN.name)
                    }
                )
            }
            composable(route = AppScreen.TIMER_RUN.name) {
                RunTimer()
            }
        }
    }
}
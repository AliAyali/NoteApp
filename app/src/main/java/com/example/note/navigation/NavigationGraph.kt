package com.example.note.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.note.presentation.screen.home.HomeScreen
import com.example.note.presentation.screen.home.task.TaskViewModel
import com.example.note.presentation.screen.item.ItemScreen
import com.example.note.presentation.screen.lock.LockScreen
import com.example.note.presentation.screen.setting.SettingScreen
import com.example.note.presentation.screen.setting.SettingViewModel
import com.example.note.presentation.screen.splash.SplashScreen

@Composable
fun SetupNavigation(
    padding: PaddingValues,
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    settingViewModel: SettingViewModel,
) {

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Splash.route,
        modifier = Modifier.padding(padding)
    ) {

        composable(
            route = NavigationScreen.Home.route
        ) {
            HomeScreen(
                navController,
                taskViewModel = taskViewModel
            )
        }

        composable(
            route = NavigationScreen.Item.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            ItemScreen(navController, itemId)
        }

        composable(
            route = NavigationScreen.Setting.route,
        ) {
            SettingScreen(navController, settingViewModel)
        }

        composable(
            route = NavigationScreen.Splash.route,
        ) {
            SplashScreen(navController)
        }

        composable(
            route = NavigationScreen.Lock.route,
        ) {
            LockScreen(navController)
        }

    }

}
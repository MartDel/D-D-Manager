package com.example.projet_android.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.projet_android.navigation.screens.AdminGameScreen
import com.example.projet_android.navigation.screens.GameScreen
import com.example.projet_android.navigation.screens.HomeScreen
import com.example.projet_android.navigation.screens.LoginScreen
import com.example.projet_android.navigation.screens.RegisterScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController, scaffoldPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController, scaffoldPadding)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController, scaffoldPadding)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController, scaffoldPadding)
        }
        composable(
            route = "${Screen.Game.route}/{game_id}",
            arguments = listOf(navArgument("game_id") { type = NavType.StringType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("game_id")
            GameScreen(gameId ?: "", navController, scaffoldPadding)
        }
        composable(
            route = "${Screen.AdminGame.route}/{game_id}",
            arguments = listOf(navArgument("game_id") { type = NavType.StringType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("game_id")
            AdminGameScreen(gameId ?: "", navController, scaffoldPadding)
        }
    }
}
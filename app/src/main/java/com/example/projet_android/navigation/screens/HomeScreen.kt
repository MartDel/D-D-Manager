package com.example.projet_android.navigation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.ui.alerts.showShortAlert
import com.example.projet_android.ui.components.contents.HomeContent
import com.example.projet_android.ui.components.footers.HomeFooter
import com.example.projet_android.ui.components.headers.MainHeader
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.GameViewModel
import com.example.projet_android.view_models.states.RequestState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavHostController, scaffoldPadding: PaddingValues) {
    val gameViewModel: GameViewModel = hiltViewModel()
    val games by gameViewModel.games.collectAsState()
    val createGameState by gameViewModel.createGameState.observeAsState()
    val fetchGamesState by gameViewModel.fetchGamesState.observeAsState()

    LaunchedEffect(Unit) {
        gameViewModel.fetchGames()
    }

    MainHeader(scaffoldPadding)

    HomeContent(
        games,
        navController,
        scaffoldPadding,
        isLoading = fetchGamesState is RequestState.Loading
    )

    HomeFooter(
        onGameAdd = { gameViewModel.addGame(it) },
        navController,
        scaffoldPadding
    )

    createGameState?.let { state ->
        when (state) {
            is RequestState.Success<*> -> {
                gameViewModel.resetCreateGameState()
                gameViewModel.fetchGames()
            }
            is RequestState.Error -> {
                showShortAlert(LocalContext.current, state.message)
            }

            else -> {}
        }
    }

    fetchGamesState?.let { state ->
        when (state) {
            is RequestState.Error -> {
                showShortAlert(LocalContext.current, state.message)
            }

            else -> {}
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ProjetAndroidTheme {
        HomeScreen(rememberNavController(), PaddingValues())
    }
}
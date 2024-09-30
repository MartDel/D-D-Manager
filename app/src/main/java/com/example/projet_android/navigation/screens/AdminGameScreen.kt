package com.example.projet_android.navigation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.R
import com.example.projet_android.model.Game
import com.example.projet_android.ui.alerts.showShortAlert
import com.example.projet_android.ui.components.contents.AdminGameContent
import com.example.projet_android.ui.components.footers.GameFooter
import com.example.projet_android.ui.components.headers.MainHeader
import com.example.projet_android.ui.components.progress.CenteredCircularProgress
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.GameDetailsViewModel
import com.example.projet_android.view_models.states.RequestState

@Composable
fun AdminGameScreen(gameId: String, navController: NavHostController, scaffoldPadding: PaddingValues) {
    val gameViewModel: GameDetailsViewModel = hiltViewModel()
    val fetchGameState by gameViewModel.fetchGameState.observeAsState()
    val addPlayerState by gameViewModel.addPlayerState.observeAsState()

    LaunchedEffect(Unit) {
        gameViewModel.fetchAdminGameById(gameId)
    }

    fetchGameState?.let { state ->
        when (state) {
            is RequestState.Loading -> {
                CenteredCircularProgress(Color.Black, size = 50.dp, scaffoldPadding)
            }

            is RequestState.Error -> {
                showShortAlert(LocalContext.current, state.message)
            }

            is RequestState.Success<*> -> {
                val game = state.data as Game
                MainHeader(scaffoldPadding, text = "${stringResource(R.string.app_name)} - ${game.name}")
                AdminGameContent(
                    game,
                    addPlayer = { player ->
                        gameViewModel.addPlayerToGame(game.id, player.name)
                        gameViewModel.fetchAdminGameById(game.id)
                    },
                    scaffoldPadding,
                    isPlayerAdding = addPlayerState is RequestState.Loading
                )
            }
        }
    }

    GameFooter(navController, scaffoldPadding)

    addPlayerState?.let { state ->
        when (state) {
            is RequestState.Error -> {
                showShortAlert(LocalContext.current, state.message)
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminGameScreenPreview() {
    ProjetAndroidTheme {
        AdminGameScreen(
            "1",
            rememberNavController(),
            PaddingValues()
        )
    }
}
package com.example.projet_android.ui.components.contents

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.model.Game
import com.example.projet_android.navigation.Screen
import com.example.projet_android.ui.alerts.showShortAlert
import com.example.projet_android.ui.components.modals.PlayerNameModal
import com.example.projet_android.ui.components.progress.CircularProgress
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.JoinPlayerViewModel
import com.example.projet_android.view_models.states.RequestState
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(
    games: List<Game>,
    navController: NavHostController,
    scaffoldPadding: PaddingValues,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
){
    val joinViewModel: JoinPlayerViewModel = hiltViewModel()
    val updatePlayerState by joinViewModel.updatePlayerState.observeAsState()

    var showDialog by remember { mutableStateOf(false) }
    var clickedGame by remember { mutableStateOf<Game?>(null) }

    val onGameClick: (Game) -> Unit = { game ->
        if (game.isPlayerAdmin) {
            navController.navigate("${Screen.AdminGame.route}/${game.id}")
        } else {
            clickedGame = game
            showDialog = true
        }
    }
    val gamesAdmin = games.filter { it.isPlayerAdmin }
    val gamesPlayer = games.filter { !it.isPlayerAdmin }
    val context = LocalContext.current

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
            .heightIn(max = 600.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgress(Color.Black, 50.dp)
        } else {
            GameListContent("Parties - Admin", gamesAdmin, onGameClick = onGameClick)
            Spacer(Modifier.size(20.dp))
            GameListContent("Parties - Player", gamesPlayer, onGameClick = onGameClick)
        }
    }

    PlayerNameModal(
        showDialog = showDialog,
        onDismiss = {
            showDialog = false
        },
        onConfirm = { playerName ->
            if (playerName.isEmpty()) {
                showShortAlert(context, "Please enter a valid username")
            } else {
                joinViewModel.updatePlayerName(clickedGame!!.id, playerName)
            }
        },
        isLoading = updatePlayerState is RequestState.Loading
    )

    updatePlayerState?.let { state ->
        when (state) {
            is RequestState.Success<*> -> {
                joinViewModel.resetUpdatePlayerState()
                navController.navigate("${Screen.Game.route}/${state.data}")
            }

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
fun HomeContentPreview() {
    ProjetAndroidTheme {
        val games1: List<Game> = listOf(
            Game("1","Partie 1", Date()),
            Game("2","Partie 2", Date()),
            Game("3","Partie avec un titre très long qui devrait être tronqué", Date()),
            Game("4","Partie 2", Date()),
            Game("5","Partie avec un titre très long qui devrait être tronqué", Date())
        )
        HomeContent(
            games1,
            rememberNavController(),
            PaddingValues()
        )
    }
}

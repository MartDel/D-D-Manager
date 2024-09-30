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
import com.example.projet_android.model.Player
import com.example.projet_android.ui.alerts.showShortAlert
import com.example.projet_android.ui.components.contents.GameContent
import com.example.projet_android.ui.components.footers.GameFooter
import com.example.projet_android.ui.components.headers.MainHeader
import com.example.projet_android.ui.components.progress.CenteredCircularProgress
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.InventoryViewModel
import com.example.projet_android.view_models.states.RequestState

@Composable
fun GameScreen(
    gameId: String,
    navController: NavHostController,
    scaffoldPadding: PaddingValues
) {
    val inventoryViewModel: InventoryViewModel = hiltViewModel()
    val fetchGameState by inventoryViewModel.fetchGameState.observeAsState()
    val usingItemState by inventoryViewModel.usingItemState.observeAsState()

    LaunchedEffect(Unit) {
        inventoryViewModel.getGameInfo(gameId)
    }

    fetchGameState?.let { state ->
        when (state) {
            is RequestState.Loading -> {
                CenteredCircularProgress(Color.Black, size = 50.dp, scaffoldPadding)
            }

            is RequestState.Success<*> -> {
                val dataPair = state.data as Pair<*, *>
                val game = dataPair.first as Game
                val player = dataPair.second as Player
                MainHeader(
                    scaffoldPadding,
                    text = "${stringResource(R.string.app_name)} - ${game.name}"
                )
                GameContent(
                    player,
                    game,
                    useItem = { inventoryViewModel.useItem(it) },
                    scaffoldPadding,
                    isAddingItem = usingItemState is RequestState.Loading
                )
            }

            is RequestState.Error -> {
                showShortAlert(LocalContext.current, state.message)
            }
        }
    }

    GameFooter(navController, scaffoldPadding)

    usingItemState?.let { state ->
        when (state) {
            is RequestState.Success<*> -> {
                inventoryViewModel.resetUsingItemState()
                inventoryViewModel.getGameInfo(gameId)
            }

            is RequestState.Error -> {
                showShortAlert(LocalContext.current, state.message)
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    ProjetAndroidTheme {
        GameScreen(
            "1",
            rememberNavController(),
            PaddingValues()
        )
    }
}
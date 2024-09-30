package com.example.projet_android.ui.components.contents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.projet_android.R
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import com.example.projet_android.model.Player
import com.example.projet_android.ui.alerts.showShortAlert
import com.example.projet_android.ui.components.headers.AdminInventoryHeader
import com.example.projet_android.ui.components.lists.InventoryItemsList
import com.example.projet_android.ui.components.modals.ItemInfoModal
import com.example.projet_android.ui.components.modals.SelectItemModal
import com.example.projet_android.ui.components.progress.CircularProgress
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.AdminInventoryViewModel
import com.example.projet_android.view_models.states.RequestState

@Composable
fun AdminInventoryContent(
    players: List<Player>,
    scaffoldPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    val adminInventoryViewModel: AdminInventoryViewModel = hiltViewModel()
    val fetchPlayerState by adminInventoryViewModel.fetchPlayerState.observeAsState()

    var showInfoDialog by remember { mutableStateOf(false) }
    var showAddItemDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    LaunchedEffect(Unit) {
        adminInventoryViewModel.fetchPlayerInventory(players[0])
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(scaffoldPadding)
    ) {
        fetchPlayerState?.let { state ->
            when (state) {
                is RequestState.Error -> {
                    showShortAlert(LocalContext.current, state.message)
                }

                is RequestState.Success<*> -> {
                    val selectedPlayer = state.data as Player
                    AdminInventoryHeader(
                        players = players,
                        selectedPlayer = selectedPlayer,
                        onPlayerChange = { adminInventoryViewModel.fetchPlayerInventory(it) }
                    )
                    InventoryItemsList(
                        items = selectedPlayer.inventory.items,
                        itemOnClick = { item ->
                            if (item.id != "ADD") {
                                showInfoDialog = true
                                selectedItem = item
                            } else {
                                showAddItemDialog = true
                            }
                        },
                        isPlayerAdmin = true
                    )
                }

                RequestState.Loading -> {
                    CircularProgress(Color.Black, 50.dp)
                }
            }
        }
    }

    ItemInfoModal(
        showDialog = showInfoDialog,
        item = selectedItem,
        onDismiss = { showInfoDialog = false },
    )

    SelectItemModal(
        showDialog = showAddItemDialog,
        onDismiss = { showAddItemDialog = false },
        onConfirm = { adminInventoryViewModel.addItemToSelectedPlayer(it.id) }
    )
}

@Preview(showBackground = true)
@Composable
fun AdminInventoryContentPreview() {
    ProjetAndroidTheme {
        val players = listOf(
            Player("1", "Player 1", Inventory(listOf(Item("1", "Item 1", R.drawable.bow.toString())))),
            Player("2", "Player 2", Inventory(listOf(Item("2", "Item 2", R.drawable.axe.toString())))),
            Player("3", "Player 3", Inventory(listOf(Item("3", "Item 3", R.drawable.crossbow.toString())))),
            Player("4", "Player 4", Inventory(listOf(Item("4", "Item 4", R.drawable.diamond.toString()))))
        )
        AdminInventoryContent(players, PaddingValues())
    }
}

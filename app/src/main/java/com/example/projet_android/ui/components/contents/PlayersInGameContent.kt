package com.example.projet_android.ui.components.contents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import com.example.projet_android.model.Player
import com.example.projet_android.ui.components.forms.AddPlayerForm
import com.example.projet_android.ui.components.lists.PlayersList

@Composable
fun PlayerInGameContent(
    players: List<Player>,
    addPlayer: (Player) -> Unit,
    modifier: Modifier = Modifier,
    isPlayerAdding: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddPlayerForm(onNewPlayer = addPlayer, isPlayerAdding = isPlayerAdding)
        PlayersList(players = players)
    }
}

@Preview(showBackground = false)
@Composable
fun PlayerInGameContentPreview() {
    val players = listOf(
        Player("1", "Player 1", Inventory(listOf(Item("1", "Item 1", R.drawable.bow.toString())))),
        Player("2", "Player 2", Inventory(listOf(Item("2", "Item 2", R.drawable.axe.toString())))),
        Player("3", "Player 3", Inventory(listOf(Item("3", "Item 3", R.drawable.crossbow.toString())))),
        Player("4", "Player 4", Inventory(listOf(Item("4", "Item 4", R.drawable.diamond.toString()))))
    )
    PlayerInGameContent(players, {})
}

package com.example.projet_android.ui.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import com.example.projet_android.model.Player
import com.example.projet_android.ui.components.cards.DeletablePlayerCard
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun PlayersList(
    players: List<Player>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(players) { player ->
            DeletablePlayerCard(player)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayersListPreview() {
    ProjetAndroidTheme {
        val players = listOf(
            Player("1", "Player 1", Inventory(listOf(Item("1", "Item 1", R.drawable.bow.toString())))),
            Player("2", "Player 2", Inventory(listOf(Item("2", "Item 2", R.drawable.axe.toString())))),
            Player("3", "Player 3", Inventory(listOf(Item("3", "Item 3", R.drawable.crossbow.toString())))),
            Player("4", "Player 4", Inventory(listOf(Item("4", "Item 4", R.drawable.diamond.toString()))))
        )
        PlayersList(players)
    }
}

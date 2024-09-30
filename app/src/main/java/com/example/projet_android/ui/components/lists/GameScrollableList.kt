package com.example.projet_android.ui.components.lists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.model.Game
import com.example.projet_android.ui.components.cards.GameListItemCard
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScrollableList(
    games: List<Game>,
    onGameClick: (Game) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(games.sortedByDescending { game -> game.createdAt }) { game ->
            GameListItemCard(game, onClick = { onGameClick(game) })
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GameScrollableListPreview() {
    ProjetAndroidTheme {
        val games: List<Game> = listOf(
            Game("1","Partie 1", Date()),
            Game("2","Partie 2", Date()),
            Game("3","Partie 3", Date())
        )
        GameScrollableList(games, {})
    }
}

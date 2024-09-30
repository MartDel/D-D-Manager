package com.example.projet_android.ui.components.contents

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.model.Game
import com.example.projet_android.ui.components.headers.SectionHeader
import com.example.projet_android.ui.components.lists.GameScrollableList
import com.example.projet_android.ui.components.texts.BasicText
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameListContent(
    sectionName: String,
    games: List<Game>,
    onGameClick: (Game) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SectionHeader(sectionName, Modifier.padding(horizontal = 15.dp))
        if (games.isEmpty()) {
            BasicText("No games found...", Modifier.fillMaxWidth())
        } else {
            GameScrollableList(
                games,
                onGameClick = onGameClick,
                Modifier
                    .padding(horizontal = 15.dp)
                    .heightIn(min = 100.dp, max = 250.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GameListPreview() {
    ProjetAndroidTheme {
        val games: List<Game> = listOf(
            Game("1","Partie 1", Date()),
            Game("2","Partie 2", Date()),
            Game("3","Partie avec un titre très long qui devrait être tronqué", Date()),
            Game("4","Partie 2", Date()),
            Game("5","Partie avec un titre très long qui devrait être tronqué", Date())
        )
        GameListContent("Admin", games, onGameClick = {})
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EmptyGameListPreview() {
    ProjetAndroidTheme {
        GameListContent("Admin", listOf(), onGameClick = {})
    }
}

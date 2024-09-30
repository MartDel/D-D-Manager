package com.example.projet_android.ui.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.model.Game
import com.example.projet_android.model.Player
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun AdminGameContent(
    game: Game,
    addPlayer: (Player) -> Unit,
    scaffoldPadding: PaddingValues,
    modifier: Modifier = Modifier,
    isPlayerAdding: Boolean = false
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
            .heightIn(max = 600.dp),
        verticalArrangement = Arrangement.Center
    ) {
        PlayerInGameContent(
            players = game.players,
            addPlayer = addPlayer,
            isPlayerAdding = isPlayerAdding
        )
        Spacer(modifier = Modifier.height(10.dp))
        AdminInventoryContent(
            game.players,
            scaffoldPadding,
            Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AdminGameContentPreview() {
    ProjetAndroidTheme {
    val game = Game(
        "1",
        "Partie 1",
        description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Finibus blandit interdum pulvinar non nostra imperdiet ut fusce. Nam egestas primis litora taciti penatibus\n" +
                "\n" +
                "Lorem ipsum odor amet, consectetuer adipiscing elit. Finibus blandit interdum pulvinar non nostra imperdiet ut fusce. Nam egestas primis litora taciti penatibus"
    )
        AdminGameContent(game, {}, PaddingValues())
    }
}

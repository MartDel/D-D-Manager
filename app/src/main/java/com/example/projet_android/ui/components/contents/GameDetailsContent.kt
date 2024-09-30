package com.example.projet_android.ui.components.contents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.model.Game
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import com.example.projet_android.model.Player
import com.example.projet_android.ui.components.texts.BasicText
import com.example.projet_android.ui.components.titles.SecondaryTitle
import com.example.projet_android.ui.components.titles.TertiaryTitle
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun GameDetailsContent(
    player: Player,
    game: Game,
    padding: Dp
){
    SecondaryTitle(player.name, Modifier.fillMaxWidth())

    Spacer(modifier = Modifier.height(16.dp))
    TertiaryTitle(
        "Status : ${game.status.replaceFirstChar(Char::titlecase)}",
        Modifier
            .padding(horizontal = padding)
            .fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))
    TertiaryTitle(
        "Description :",
        Modifier
            .padding(horizontal = padding)
            .fillMaxWidth()
    )
    BasicText(
        game.getSafeDescription(),
        maxLines = 8,
        modifier = Modifier.padding(horizontal = padding)
    )
}

@Preview(showBackground = true)
@Composable
fun GameDetailsContentPreview() {
    ProjetAndroidTheme {
        GameDetailsContent(
            Player(
                "1",
                "TestUsername",
                Inventory(
                    items = listOf(
                        Item("1", "Item 1", R.drawable.armor1.toString()),
                        Item("2", "Item 2", R.drawable.axe.toString()),
                        Item("3", "Item 3", R.drawable.bow.toString()),
                        Item("4", "Item 4", R.drawable.coins.toString()),
                        Item("5", "Item 5", R.drawable.heal.toString()),
                        Item("6", "Item 6", R.drawable.diamond.toString()),
                        Item("7", "Item 7", R.drawable.food.toString()),
                        Item("8", "Item 8", R.drawable.knife.toString()),
                    )
                )
            ),
            Game(
                "1",
                "Partie 1",
                description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Finibus blandit interdum pulvinar non nostra imperdiet ut fusce. Nam egestas primis litora taciti penatibus\n" +
                        "\n" +
                        "Lorem ipsum odor amet, consectetuer adipiscing elit. Finibus blandit interdum pulvinar non nostra imperdiet ut fusce. Nam egestas primis litora taciti penatibus"
            ),
            10.dp
        )
    }
}

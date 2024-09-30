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
import com.example.projet_android.R
import com.example.projet_android.model.Game
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import com.example.projet_android.model.Player
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun GameContent(
    player: Player,
    game: Game,
    useItem: (Item) -> Unit,
    scaffoldPadding: PaddingValues,
    modifier: Modifier = Modifier,
    isAddingItem: Boolean = false
){
    val padding = 10.dp

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
            .heightIn(max = 600.dp),
        verticalArrangement = Arrangement.Center
    ) {
        GameDetailsContent(player, game, padding)
        Spacer(modifier = Modifier.height(24.dp))
        InventoryContent(
            player.inventory,
            useItem = useItem,
            scaffoldPadding,
            Modifier.padding(horizontal = padding),
            isAddingItem = isAddingItem
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameContentPreview() {
    ProjetAndroidTheme {
        GameContent(
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
            {},
            PaddingValues()
        )
    }
}

package com.example.projet_android.ui.components.modals

 import androidx.compose.material3.AlertDialog
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.tooling.preview.Preview
 import com.example.projet_android.R
 import com.example.projet_android.model.Inventory
 import com.example.projet_android.model.Item
 import com.example.projet_android.model.Player
 import com.example.projet_android.ui.components.buttons.OutlineButton
 import com.example.projet_android.ui.components.lists.PlayerNamesList
 import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun SelectPlayerModal(
    showDialog: Boolean,
    players: List<Player>,
    onDismiss: () -> Unit = {},
    onConfirm: (Player) -> Unit = {}
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Select a player")
            },
            text = {
                PlayerNamesList(
                    players,
                    onNameClick = { player ->
                        onConfirm(player)
                        onDismiss()
                    }
                )
            },
            confirmButton = {
                OutlineButton("Cancel", onClick = onDismiss)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectPlayerModalPreview() {
    ProjetAndroidTheme {
        val players = listOf(
            Player("1", "Player 1", Inventory(listOf(Item("1", "Item 1", R.drawable.bow.toString())))),
            Player("2", "Player 2", Inventory(listOf(Item("2", "Item 2", R.drawable.axe.toString())))),
            Player("3", "Player 3", Inventory(listOf(Item("3", "Item 3", R.drawable.crossbow.toString())))),
            Player("4", "Player 4", Inventory(listOf(Item("4", "Item 4", R.drawable.diamond.toString()))))
        )
        SelectPlayerModal(true, players)
    }
}
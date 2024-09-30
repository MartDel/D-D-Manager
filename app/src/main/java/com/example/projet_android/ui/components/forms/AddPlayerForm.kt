package com.example.projet_android.ui.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Player
import com.example.projet_android.ui.components.buttons.ValidateButton
import com.example.projet_android.ui.components.inputs.TextInput

@Composable
fun AddPlayerForm(
    onNewPlayer: (Player) -> Unit,
    modifier: Modifier = Modifier,
    isPlayerAdding: Boolean = false
) {
    var playerName by remember { mutableStateOf("") }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextInput(
            value = playerName,
            onValueChange = { playerName = it },
            placeholder = "Enter a player name",
            modifier = Modifier.weight(1f)
        )

        ValidateButton(
            text = "Add",
            onClick = {
                if (playerName.isNotEmpty()) {
                    onNewPlayer(Player(id="1", playerName, Inventory(listOf())))
                    playerName = ""
                }
            },
            isLoading = isPlayerAdding
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddPlayerFormPreview() {
    AddPlayerForm({})
}
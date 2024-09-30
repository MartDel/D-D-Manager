package com.example.projet_android.ui.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import com.example.projet_android.model.Player
import com.example.projet_android.ui.components.buttons.ImageButton
import com.example.projet_android.ui.components.modals.SelectPlayerModal
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun AdminInventoryHeader(players: List<Player>, selectedPlayer: Player, onPlayerChange: (Player) -> Unit, modifier: Modifier = Modifier){
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF3E2723))
            .height(56.dp)
            .clickable { showDialog = true },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Inventory - ${selectedPlayer.name}",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(5.dp))
        ImageButton(
            R.drawable.caret_down,
            "Caret arrow",
            onClick = { showDialog = true },
            modifier = modifier.size(20.dp)
        )
    }

    SelectPlayerModal(
        showDialog,
        players,
        onDismiss = { showDialog = false },
        onConfirm = onPlayerChange
    )
}

@Preview(showBackground = true)
@Composable
fun InventoryHeaderPreview() {
    ProjetAndroidTheme {
        val players = listOf(
            Player("1", "Player 1", Inventory(listOf(Item("1", "Item 1", R.drawable.bow.toString())))),
            Player("2", "Player 2", Inventory(listOf(Item("2", "Item 2", R.drawable.axe.toString())))),
            Player("3", "Player 3", Inventory(listOf(Item("3", "Item 3", R.drawable.crossbow.toString())))),
            Player("4", "Player 4", Inventory(listOf(Item("4", "Item 4", R.drawable.diamond.toString()))))
        )
        AdminInventoryHeader(players, players[0], {})
    }
}
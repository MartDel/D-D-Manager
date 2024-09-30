package com.example.projet_android.ui.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.model.Item
import com.example.projet_android.ui.components.cards.InventoryItemCard
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun InventoryItemsList(
    items: List<Item>,
    itemOnClick: (Item) -> Unit,
    modifier: Modifier = Modifier,
    isPlayerAdmin: Boolean = false
){
    val itemsToDisplay = if (isPlayerAdmin) items + Item("ADD", "ADD", R.drawable.add.toString()) else items

    Column (modifier = Modifier.background(Color(0xFF6E5C53), shape = RoundedCornerShape(0.dp)))
    {
        for (row in 0 until 4) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0 until 5) {
                    val index = row * 5 + col
                    val item = if (index < itemsToDisplay.size) itemsToDisplay[index] else null
                    InventoryItemCard(item, onClick = itemOnClick)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryItemsListPreview() {
    ProjetAndroidTheme {
        InventoryItemsList(
            items = listOf(
                Item("1", "Item 1", R.drawable.information.toString()),
                Item("2", "Item 2", R.drawable.information.toString()),
                Item("3", "Item 3", R.drawable.information.toString()),
                Item("4", "Item 4", R.drawable.information.toString()),
                Item("5", "Item 5", R.drawable.information.toString()),
                Item("6", "Item 6", R.drawable.information.toString()),
                Item("7", "Item 7", R.drawable.information.toString()),
            ),
            {},
            isPlayerAdmin = true
        )
    }
}

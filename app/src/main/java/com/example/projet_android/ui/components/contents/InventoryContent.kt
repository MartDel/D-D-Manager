package com.example.projet_android.ui.components.contents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet_android.R
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import com.example.projet_android.ui.components.headers.SectionHeader
import com.example.projet_android.ui.components.lists.InventoryItemsList
import com.example.projet_android.ui.components.modals.ItemInteractModal
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun InventoryContent(
    inventory: Inventory,
    useItem: (Item) -> Unit,
    scaffoldPadding: PaddingValues,
    modifier: Modifier = Modifier,
    isAddingItem: Boolean = false
){
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(scaffoldPadding)
    ) {
        SectionHeader("Inventory")
        InventoryItemsList(
            inventory.items,
            itemOnClick = { item ->
                showDialog = true
                selectedItem = item
            }
        )
    }

    ItemInteractModal(
        showDialog,
        selectedItem,
        onDismiss = { showDialog = false },
        onConfirm = { useItem(selectedItem!!) },
        isAddingItem = isAddingItem
    )
}

@Preview(showBackground = true)
@Composable
fun InventoryContentPreview() {
    ProjetAndroidTheme {
        InventoryContent(
            Inventory(
                items = listOf(
                    Item("1", "Item 1", R.drawable.information.toString()),
                    Item("2", "Item 2", R.drawable.information.toString()),
                    Item("3", "Item 3", R.drawable.information.toString()),
                    Item("4", "Item 4", R.drawable.information.toString()),
                    Item("5", "Item 5", R.drawable.information.toString()),
                )
            ),
            {},
            PaddingValues()
        )
    }
}

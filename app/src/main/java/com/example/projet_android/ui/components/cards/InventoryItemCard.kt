package com.example.projet_android.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.model.Item
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun InventoryItemCard(item: Item?, modifier: Modifier = Modifier, onClick: (Item) -> Unit = {}) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(64.dp)
            .padding(4.dp)
            .background(Color(0xFF474042), shape = RoundedCornerShape(8.dp))
            .clickable {
                if (item != null) onClick(item)
            },
        contentAlignment = Alignment.Center
    ) {
        if (item != null) {
            Image(
                painter = painterResource(id = item.getResId(context)),
                contentDescription = "Inventory item",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(4.dp).fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryItemCardPreview() {
    ProjetAndroidTheme {
        InventoryItemCard(Item("1", "test", "information"))
    }
}
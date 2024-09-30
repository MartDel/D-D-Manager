package com.example.projet_android.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.model.Item
import com.example.projet_android.ui.components.buttons.ImageButton
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun ItemToAddCard(item: Item, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ImageButton(item.getResId(context), item.name, onClick, modifier = Modifier.padding(8.dp))
        Text(text = item.name, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = false)
@Composable
fun ItemToAddCardPreview() {
    ProjetAndroidTheme {
        val item = Item("2", "Armor 1", R.drawable.armor1.toString(), "A solid piece of armor for protection")
        ItemToAddCard(item, {})
    }
}
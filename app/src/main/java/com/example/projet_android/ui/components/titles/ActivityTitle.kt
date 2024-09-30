package com.example.projet_android.ui.components.titles

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet_android.ui.theme.IrishGrover


@Composable
fun ActivityTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 32.sp,
        fontFamily = IrishGrover,
        color = Color.Black,
        modifier = modifier.padding(top = 16.dp),
        textAlign = TextAlign.Center
    )
}
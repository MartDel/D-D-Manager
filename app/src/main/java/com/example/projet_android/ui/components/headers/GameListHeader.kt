package com.example.projet_android.ui.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun SectionHeader(sectionName: String, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF3E2723))
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = sectionName,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameListPreview() {
    ProjetAndroidTheme {
        SectionHeader("Parties - Admin")
    }
}
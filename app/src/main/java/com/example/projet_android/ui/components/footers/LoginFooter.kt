package com.example.projet_android.ui.components.footers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.projet_android.ui.components.buttons.ValidateButton

@Composable
fun LoginFooter(scaffoldPadding: PaddingValues, btnText: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        ValidateButton(text = btnText, color = Color(0xFF6D4C41), onClick = onClick)
    }
}

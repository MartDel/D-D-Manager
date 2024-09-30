package com.example.projet_android.ui.components.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun CenteredCircularProgress(
    color: Color,
    size: Dp,
    scaffoldPadding: PaddingValues,
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    progressModifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgress(
            color = color,
            size = size,
            modifier = rowModifier,
            progressModifier = progressModifier
        )
    }
}

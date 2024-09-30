package com.example.projet_android.ui.components.footers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.R
import com.example.projet_android.navigation.Screen
import com.example.projet_android.ui.components.buttons.ImageButton
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun GameFooter(navController: NavHostController, scaffoldPadding: PaddingValues, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = scaffoldPadding.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            ImageButton(
                R.drawable.arrow_back,
                "Back icon",
                onClick = { navController.navigate(Screen.Home.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameFooterPreview() {
    ProjetAndroidTheme {
        GameFooter(rememberNavController(), PaddingValues())
    }
}

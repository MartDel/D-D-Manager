package com.example.projet_android.navigation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.navigation.Screen
import com.example.projet_android.ui.components.contents.LoginContent
import com.example.projet_android.ui.components.footers.LoginFooter
import com.example.projet_android.ui.components.headers.MainHeader
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun LoginScreen(navController: NavHostController, scaffoldPadding: PaddingValues) {
    MainHeader(scaffoldPadding)

    LoginContent(scaffoldPadding, navController)

    LoginFooter(scaffoldPadding, "Register", onClick = {
        navController.navigate(Screen.Register.route)
    })
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ProjetAndroidTheme {
        LoginScreen(rememberNavController(), PaddingValues())
    }
}
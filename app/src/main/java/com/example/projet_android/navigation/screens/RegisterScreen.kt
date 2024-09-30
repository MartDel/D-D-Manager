package com.example.projet_android.navigation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.navigation.Screen
import com.example.projet_android.ui.components.contents.RegisterContent
import com.example.projet_android.ui.components.footers.LoginFooter
import com.example.projet_android.ui.components.headers.MainHeader
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun RegisterScreen(navController: NavHostController, scaffoldPadding: PaddingValues) {
    MainHeader(scaffoldPadding)

    RegisterContent(scaffoldPadding, navController)

    LoginFooter(scaffoldPadding, "Login", onClick = {
        navController.navigate(Screen.Login.route)
    })
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    ProjetAndroidTheme {
        RegisterScreen(rememberNavController(), PaddingValues())
    }
}
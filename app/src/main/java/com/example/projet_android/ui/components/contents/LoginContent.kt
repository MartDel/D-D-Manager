package com.example.projet_android.ui.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.navigation.Screen
import com.example.projet_android.ui.alerts.showShortAlert
import com.example.projet_android.ui.components.buttons.ValidateButton
import com.example.projet_android.ui.components.cards.RoundedDarkCard
import com.example.projet_android.ui.components.inputs.EmailInput
import com.example.projet_android.ui.components.inputs.PasswordInput
import com.example.projet_android.ui.components.titles.CardTitle
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.AuthViewModel
import com.example.projet_android.view_models.states.RequestState

@Composable
fun LoginContent(scaffoldPadding: PaddingValues, navController: NavHostController, modifier: Modifier = Modifier){
    val authViewModel: AuthViewModel = hiltViewModel()
    val loginState by authViewModel.loginState.observeAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
        verticalArrangement = Arrangement.Center
    ) {
        RoundedDarkCard {
            CardTitle("Login")
            Spacer(modifier = Modifier.height(16.dp))

            EmailInput(email, { email = it })
            Spacer(modifier = Modifier.height(16.dp))

            PasswordInput(password, { password = it })
            Spacer(modifier = Modifier.height(16.dp))

            ValidateButton(
                "Submit",
                onClick = { authViewModel.login(email, password) },
                isLoading = loginState is RequestState.Loading
            )
        }
    }

    loginState?.let { state ->
        when (state) {
            is RequestState.Success<*> -> {
                authViewModel.resetLoginState()
                navController.navigate(Screen.Home.route) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            }
            is RequestState.Error -> {
                showShortAlert(LocalContext.current, state.message)
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ProjetAndroidTheme {
        LoginContent(PaddingValues(), rememberNavController())
    }
}
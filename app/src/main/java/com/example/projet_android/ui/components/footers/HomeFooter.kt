package com.example.projet_android.ui.components.footers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.R
import com.example.projet_android.navigation.Screen
import com.example.projet_android.ui.components.buttons.ImageButton
import com.example.projet_android.ui.components.modals.AddGameModal
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.AuthViewModel

@Composable
fun HomeFooter(onGameAdd: (String) -> Unit, navController: NavHostController, scaffoldPadding: PaddingValues, modifier: Modifier = Modifier){
    val authViewModel: AuthViewModel = hiltViewModel()

    var showDialog by remember { mutableStateOf(false) }

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
                R.drawable.logout,
                "Logout icon",
                onClick = {
                    authViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            )
            ImageButton(
                R.drawable.add,
                "Add icon",
                onClick = { showDialog = true }
            )
        }
    }

    AddGameModal(
        showDialog,
        onConfirm = onGameAdd,
        onDismiss = { showDialog = false }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeFooterPreview() {
    ProjetAndroidTheme {
        HomeFooter({}, rememberNavController(), PaddingValues())
    }
}

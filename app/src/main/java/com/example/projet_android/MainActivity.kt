package com.example.projet_android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.navigation.NavGraph
import com.example.projet_android.navigation.Screen
import com.example.projet_android.ui.theme.ProjetAndroidTheme
import com.example.projet_android.view_models.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetAndroidTheme {
                val navController = rememberNavController()

                val authViewModel: AuthViewModel = hiltViewModel()
                val isLoggedIn = authViewModel.isConnected()

                LaunchedEffect(Unit) {
                    if (isLoggedIn) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    } else {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    }
                }

                Scaffold { innerPadding ->
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    NavGraph(navController = navController, scaffoldPadding = innerPadding)
                }
            }
        }
    }
}

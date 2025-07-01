package com.example.sportteammakerproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportteammakerproject.uii.LoginScreen
import com.example.sportteammakerproject.uii.ScoringScreen
import com.example.sportteammakerproject.uii.SignUpScreen
import com.example.sportteammakerproject.uii.TestScreen
import com.example.sportteammakerproject.viewModel.AuthViewModel

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ScoringScreen", builder = {
        composable("test") {
            TestScreen()
        }
        composable("LoginScreen") {
            LoginScreen(navController, authViewModel)
        }
        composable("SignUpScreen") {
            SignUpScreen(navController, authViewModel)
        }
        composable("ScoringScreen") {
            ScoringScreen(navController, authViewModel)
        }
    })


}
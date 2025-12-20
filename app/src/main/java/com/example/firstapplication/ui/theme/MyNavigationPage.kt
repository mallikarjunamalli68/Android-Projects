package com.example.firstapplication.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapplication.AuthViewModel
import com.example.firstapplication.HomeActivityPage
import com.example.firstapplication.LoginActivity
import com.example.firstapplication.SignUpActivity

@Composable

fun MyNavigation(modifier: Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginActivity(modifier = Modifier, navController, authViewModel)
        }
        composable("signup") {
            SignUpActivity(modifier = Modifier, navController, authViewModel)
        }
        composable("home") {
            HomeActivityPage(modifier = Modifier, navController, authViewModel)
        }
    }
}
package com.example.firstapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun HomeActivityPage(modifier: Modifier, navController: NavHostController, authViewModel: AuthViewModel) {
    val authSate = authViewModel.authState.observeAsState()

    LaunchedEffect(authSate.value) {
        when (authSate.value) {
            is AuthSate.UnAuthenticated -> {
                navController.navigate("login")
            }
            else -> Unit
        }
    }
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                authViewModel.signOut()
            }
        ) {
            Text(text = "SignOut")
        }
    }
}
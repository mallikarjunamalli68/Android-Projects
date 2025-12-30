package com.example.advancedloginandsignup.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.advancedloginandsignup.data.local.AppDatabase
import com.example.advancedloginandsignup.data.local.AuthRepository
import com.example.advancedloginandsignup.ui_forgot.ForgotPasswordViewModel
import com.example.advancedloginandsignup.ui_login.LoginViewModel
import com.example.advancedloginandsignup.ui_signup.SignUpViewModel
import com.example.advancedloginandsignup.ui.theme.AdvancedLoginAndSignUpTheme
import com.example.advancedloginandsignup.ui_forgot.ForgotPassword
import com.example.advancedloginandsignup.ui_login.LoginScreen
import com.example.advancedloginandsignup.ui_signup.SignUpScreen
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedLoginAndSignUpTheme {
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current
                val sessionManager = remember { SessionManager(context) }
                val isLoggedIn by sessionManager.isLoggedIn.collectAsState(initial = false)
                val database = AppDatabase.getInstance(context)
                val userDao = database.userDao()
                val repository = AuthRepository(userDao)


                NavHost(navController = navController,
                    startDestination = if (isLoggedIn) "home" else "login"){
                    composable("login"){

                        val viewModel: LoginViewModel = viewModel(
                            factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                                    return LoginViewModel(repository ,
                                        sessionManager) as T
                                }
                            }
                        )
                        val state by viewModel.state.collectAsState()

                        LaunchedEffect(state.isSuccess) {
                            if (state.isSuccess){
                                navController.navigate("home"){
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        LoginScreen(
                            state = state,
                            onEmailChanged = viewModel::onEmailChange,
                            onPasswordChanged = viewModel::onPasswordChange,
                            onLoginClicked = viewModel::onLoginChange,
                            onForgotPasswordClicked = {
                                navController.navigate("forgot")
                            },
                            onSignUpClicked = {
                                navController.navigate("signup")
                            },
                            clearError = viewModel::clearError
                        )
                    }

                    composable("signup"){
                        val viewModel: SignUpViewModel = viewModel()
                        val state by viewModel.state.collectAsState()

                        LaunchedEffect(state.isSuccess) {
                            if (state.isSuccess){
                                navController.navigate("home"){
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        SignUpScreen(
                            state = state,
                            onEmailChanged = viewModel::onEmailChange,
                            onPasswordChanged = viewModel::onPasswordChange,
                            onSignUpClicked = viewModel::onSignUpClicked,
                            onConfirmPasswordChanged = viewModel::onCofirmPassword,
                            onLoginClicked = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable("forgot"){
                        val viewModel: ForgotPasswordViewModel = viewModel()
                        val state by viewModel.state.collectAsState()

                        LaunchedEffect(state.isSuccess) {
                            if (state.isSuccess){
                                navController.popBackStack()
                            }
                        }

                        ForgotPassword(
                            state = state,
                            onEmailChanged = viewModel::onEmailChange,
                            onSubmitClicked = viewModel::onSubmitClicked
                        )
                    }
                    
                    composable("home") {
                        HomeScreen (
                            onLogoutClicked = {
                                coroutineScope.launch{
                                    sessionManager.setLoggedIn(false)
                                }
                                navController.navigate("login"){
                                    popUpTo("home"){
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

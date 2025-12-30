package com.example.advancedloginandsignup.ui_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedloginandsignup.data.local.AuthRepository
import com.example.advancedloginandsignup.pages.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: AuthRepository,
                     private val sessionManager: SessionManager) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEmailChange(email: String){
        _state.value = _state.value.copy(
            email = email,
            error = null
        )
    }

    fun onPasswordChange(password: String){
        _state.value = _state.value.copy(
            password = password,
            error = null
        )
    }

    fun clearError(){
        _state.value = _state.value.copy(
            error = null
        )
    }
    fun onLoginChange(){
        val email = _state.value.email
        val password = _state.value.password

        when {
            email.isEmpty() -> {
                _state.value = _state.value.copy(
                    error = "Email can't be empty"
                )
                return
            }

            password.isEmpty() -> {
                _state.value = _state.value.copy(
                    error = "Password can't be empty"
                )
                return
            }

            password.length <6 -> {
                _state.value = _state.value.copy(
                    error = "Password must contain more than 6 character"
                )
                return
            }
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = null,
                isSuccess = false
            )
            val success = repository.login(email, password)

            if (success) {
                sessionManager.setLoggedIn(true)

                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Invalid email or password"
                )
            }
        }
    }
}
package com.example.advancedloginandsignup.ui_signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedloginandsignup.data.local.AuthRepository
import com.example.advancedloginandsignup.pages.SessionManager
import com.example.advancedloginandsignup.ui_login.SignUpState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AuthRepository,
                      private val sessionManager: SessionManager
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()

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

    fun onCofirmPassword(confirmPassword: String){
        _state.value = _state.value.copy(
            confirmPassword = confirmPassword,
            error = null
        )
    }

    fun onSignUpClicked(){
        val email = _state.value.email
        val password = _state.value.password
        val confirmPassword = _state.value.confirmPassword

        when {
            email.isEmpty() -> {
                _state.value = _state.value.copy(
                    error = "email can't be empty"
                )
                return
            }
            password.isEmpty() -> {
                _state.value = _state.value.copy(
                    error = "password can't be empty"
                )
                return
            }
            password.length <6 -> {
                _state.value = _state.value.copy(
                    error = "Password must contain more than 6 character"
                )
                return
            }
            confirmPassword.isEmpty() -> {
                _state.value = _state.value.copy(
                    error = "confirm password can't be empty"
                )
                return
            }
            password != confirmPassword -> {
                _state.value = _state.value.copy(
                    error = "password & confirm password must match"
                )
            }
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = null,
                isSuccess = false
            )

            val result = repository.signUp(email, password)

            if (result.isSuccess) {
                sessionManager.setLoggedIn(true)

                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message ?: "Signup failed"
                )
            }
        }
    }
}
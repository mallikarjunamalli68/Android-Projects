package com.example.advancedloginandsignup.ui_forgot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedloginandsignup.ui_login.ForgotPasswordState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel: ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordState())
    val state: StateFlow<ForgotPasswordState> = _state.asStateFlow()

    fun onEmailChange(email: String){
        _state.value = _state.value.copy(
            email = email,
            error = null
        )
    }

    fun onSubmitClicked(){
        if (state.value.email.isEmpty()){
            _state.value = _state.value.copy(
                error = "Email can't be empty"
            )
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true, error = null)

            delay(2000)

            _state.value = _state.value.copy(
                isLoading = false,
                isSuccess = true
            )
        }
    }
}
package com.example.firstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthSate>()
    val authState: LiveData<AuthSate> = _authState

    init {
        checkAuthState()
    }

    fun checkAuthState(){
        if(auth.currentUser == null){
            _authState.value = AuthSate.UnAuthenticated
        } else {
            _authState.value = AuthSate.Authenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthSate.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthSate.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthSate.Authenticated
                } else {
                    _authState.value = AuthSate.Error(task.exception?.message ?: "Unknown error")
                }
            }
        }

    fun signUp(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthSate.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthSate.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthSate.Authenticated
                    } else {
                    _authState.value = AuthSate.Error(task.exception?.message ?: "Unknown error")
                    }
            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value = AuthSate.UnAuthenticated
    }
}

sealed class AuthSate {
    object Authenticated : AuthSate()
    object UnAuthenticated : AuthSate()
    data class Error(val message: String) : AuthSate()
    object Loading : AuthSate()
}
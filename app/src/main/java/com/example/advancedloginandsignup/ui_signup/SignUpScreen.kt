package com.example.advancedloginandsignup.ui_signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.example.advancedloginandsignup.ui_login.SignUpState

@Composable
fun SignUpScreen(
    state: SignUpState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onSignUpClicked: () -> Unit,
    onLoginClicked: () -> Unit
){

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SignUp",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChanged,
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "email@gmail.com") },
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            }
        )

        var passwordVisible by remember { mutableStateOf(false) }
        var confirmPasswordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChanged,
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "password") },
            placeholder = { Text(text = "Password") },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Visibility",
                    modifier = Modifier
                        .clickable { passwordVisible = !passwordVisible })
            }
        )

        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange = onConfirmPasswordChanged,
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "confirm password") },
            placeholder = { Text(text = "confirm password") },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Visibility",
                    modifier = Modifier
                        .clickable { confirmPasswordVisible = !confirmPasswordVisible })
            }
        )

        Button(onClick = onSignUpClicked,
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "SignUp")
        }

        TextButton( onClick = onLoginClicked) {
            Text(text = "Already have an account? Login",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp)
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        state = SignUpState(
            email = "test@email.com",
            password = "123456",
            confirmPassword = "123456",
            error = "Invalid email or password"
        ),
        onEmailChanged = {},
        onPasswordChanged = {},
        onConfirmPasswordChanged = {},
        onSignUpClicked = {},
        onLoginClicked = {}
    )
}


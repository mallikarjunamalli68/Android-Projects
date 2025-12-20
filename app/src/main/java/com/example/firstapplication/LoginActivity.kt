package com.example.firstapplication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState

@Composable
fun LoginActivity(modifier: Modifier, navController: NavHostController, authViewModel: AuthViewModel){

    val authSate = authViewModel.authState.observeAsState()

    LaunchedEffect(authSate.value) {
        when (authSate.value) {
            is AuthSate.Authenticated -> {
                navController.navigate("home")
            }
            else -> Unit
            }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.backgroud),
                contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (title, column) = createRefs()
            Text(
                text = "Login",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .constrainAs(title){
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top, margin = 100.dp)
                },
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Column(
                modifier = Modifier
                    .width(350.dp)
                    .height(400.dp)
                    .background(Color.White,
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp,
                            bottomEnd = 50.dp, bottomStart = 50.dp))
                    .constrainAs(column){
                        centerHorizontallyTo(parent)
                        top.linkTo(title.bottom, margin = 20.dp)
                    }

            ) {
                Text(text = "Email",
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)

                var textEmail by rememberSaveable {
                    mutableStateOf("") }
                var textPassword by rememberSaveable {
                    mutableStateOf("") }
                var passwordVisible by rememberSaveable {
                    mutableStateOf(false) }

                OutlinedTextField(
                    value = textEmail,
                    onValueChange = {textEmail = it
                    },
                    label = { Text(text = "email@example.com")},
                    modifier = Modifier.padding(start = 10.dp, end = 20.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    leadingIcon = {
                        Icon( imageVector = Icons.Default.Email,
                            contentDescription = "emailIcon",
                            tint = Color.Black)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Text(text = "Password",
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                
                OutlinedTextField(
                    value = textPassword,
                    onValueChange = {textPassword = it},
                    label = { Text(text = "password")},
                    modifier = Modifier.padding(start = 10.dp, end = 20.dp)
                        .clickable(onClick= { /* Ignoring onClick */ })
                        .align(alignment = Alignment.CenterHorizontally),
                    leadingIcon = {
                        Icon( imageVector = Icons.Default.Lock,
                            contentDescription = "emailIcon",
                            tint = Color.Black)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Visibility",
                            tint = Color.Black,
                            modifier = Modifier.clickable {
                                passwordVisible = !passwordVisible
                            }
                        )

                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Button(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(top = 30.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        authViewModel.login(textEmail, textPassword)
                    }
                ){
                    Text(text = "Login",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
                TextButton( modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                    onClick = { /* Ignoring onClick */ }) {
                    Text(text = "Forgot Password?",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Don't have an account?",
                        modifier = Modifier
                            .padding(top = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)

                    TextButton( onClick = {
                        navController.navigate("signup")
                    }) { 
                        Text(text = "SignUp",
                            textDecoration = TextDecoration.Underline,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
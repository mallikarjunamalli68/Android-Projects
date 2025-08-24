package com.example.uidesigndemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(){
    Column {

        Scaffold(
            topBar = {
                TopAppBar()
            },
            bottomBar = {
                NavBar()
            }
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                Column {
                    WalletScreen()
                    UserCards()
                    FinanceSection()
                    Spacer(modifier = Modifier.padding(10.dp))
                    CurrencySection()
                }
            }
        }
    }
}
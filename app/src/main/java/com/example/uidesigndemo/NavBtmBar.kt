package com.example.uidesigndemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun NavBar(modifier: Modifier = Modifier) {
    var selected by remember {
        mutableIntStateOf(0)
    }

    val navItemList = listOf(
        NavItem(label = "Home", icon = Icons.Default.Home),
        NavItem(label = "Cart", icon = Icons.Rounded.ShoppingCart),
        NavItem(label = "Notifications", icon = Icons.Default.Notifications),
        NavItem(label = "Account", icon = Icons.Default.AccountCircle)
    )
    NavigationBar {
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(selected = selected == index,
                onClick = { selected = index },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = "Icon")
                },
                label = {
                    Text(text = navItem.label)
                }
            )
        }
    }
}

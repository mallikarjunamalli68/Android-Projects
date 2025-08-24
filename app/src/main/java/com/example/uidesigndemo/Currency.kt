package com.example.uidesigndemo

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class Currency(
    val name: String,
    val buy: Double,
    val sell: Double,
    val icon: ImageVector
)

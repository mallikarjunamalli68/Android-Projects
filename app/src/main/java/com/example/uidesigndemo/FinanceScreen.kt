package com.example.uidesigndemo

import android.support.v4.os.IResultReceiver.Default
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val financeList:List<Finance> = listOf(
    Finance(
        icon = Icons.Default.Star,
        name = "My\nFinance",
        background = Color.Blue
    ),
    Finance(
        icon = Icons.Default.Star,
        name = "My\nFinance",
        background = Color.Blue
    ),
    Finance(
        icon = Icons.Default.Star,
        name = "My\nFinance",
        background = Color.Blue
    ),
    Finance(
        icon = Icons.Default.Star,
        name = "My\nFinance",
        background = Color.Blue
    )
)

@Composable
fun FinanceSection(){
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(text = "Finance",
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(5.dp))
        LazyRow {
            items(financeList.size){
                FinanceItem(it)
            }
        }
    }
}

@Composable
fun FinanceItem(
    index: Int
){
    val finance = financeList[index]
    var lastPaddingEnd = 0.dp

    if(index == financeList.size-1){
        lastPaddingEnd = 16.dp
    }

    Box(modifier = Modifier.padding(start = 15.dp, end = lastPaddingEnd)) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.LightGray)
                .size(125.dp)
                .clickable {}
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Green)
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = finance.icon,
                    contentDescription = finance.name,
                    tint = Color.White
                )
            }

            Text(
                text = finance.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
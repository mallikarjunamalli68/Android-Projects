package com.example.uidesigndemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserCards() {
    val cardList = listOf(
        Card("Credit", "Mallikarjuna", "XXXX XXXX XXXX 4324", "43959.00"),
        Card("Credit", "Mallikarjuna", "XXXX XXXX XXXX 4324", "43959.00"),
        Card("Credit", "Mallikarjuna", "XXXX XXXX XXXX 4324", "43959.00")
    )
    HorizontalCard(cardList)
}

    @Composable
    fun HorizontalCard(cardList: List<Card>) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cardList) {card ->
                CardItem(card = card)
            }
        }
    }

@Composable
fun CardItem(card: Card){
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(170.dp)
            .background(
                color = Color(0xFF1E88E5),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = card.type,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = card.cardNumber,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 13.sp
            )
            Text(
                text = card.holderName,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = card.amount,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
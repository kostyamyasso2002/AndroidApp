package com.kostyamyasso.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Logo() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "NinjaFood logo",
            modifier = Modifier.size(160.dp)
        )
        Text(
            text = "FoodNinja",
            fontSize = 40.sp,
            color = Color(0xFF53E88B),
            fontWeight = FontWeight.Bold
        )
        Text(text = "Deliver Favourite food", fontSize = 14.sp)
    }
}

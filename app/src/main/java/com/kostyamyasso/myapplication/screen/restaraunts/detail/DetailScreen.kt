package com.kostyamyasso.myapplication.screen.restaraunts.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(data: String) {
    val list = data.split(",")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = list[0], fontSize = 30.sp)
        Text(text = "Delivery time: ${list[1]}")
    }
}
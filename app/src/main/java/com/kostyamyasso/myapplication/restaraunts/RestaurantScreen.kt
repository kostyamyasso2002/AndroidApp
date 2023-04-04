package com.kostyamyasso.myapplication.restaraunts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.kostyamyasso.myapplication.R
import com.kostyamyasso.myapplication.data.RemoteRestaurant

@Composable
fun RestaurantScreen(restaurantViewModel: RestaurantViewModel = viewModel()) {
    val viewState by restaurantViewModel.viewState.collectAsState()

    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item { Text(text = "Nearest Restaurants", fontSize = 30.sp) }
        items(viewState.nearestRestaurants) { restaurant ->
            RestaurantView(
                remoteRestaurant = restaurant,
                obtainClick = {
                    restaurantViewModel.obtainEvent(
                        RestaurantEvent.ChooseNearestRest(
                            restaurant.id
                        )
                    )
                })
        }
        item { Text(text = "Popular Restaurants", fontSize = 30.sp) }
        items(viewState.popularRestaurants) { restaurant ->
            RestaurantView(
                remoteRestaurant = restaurant,
                obtainClick = {
                    restaurantViewModel.obtainEvent(
                        RestaurantEvent.ChoosePopularRest(
                            restaurant.id
                        )
                    )
                })
        }
    }
}

@Composable
fun RestaurantView(remoteRestaurant: RemoteRestaurant, obtainClick: () -> Unit) {
    Box(modifier = Modifier
        .width(150.dp)
        .clickable { obtainClick() }
        .border(BorderStroke(5.dp, SolidColor(Color.Transparent)))
        .shadow(3.dp)
        .padding(15.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = remoteRestaurant.image,
                contentDescription = remoteRestaurant.name,
                error = painterResource(
                    id = R.drawable.not_found
                )
            )
            Text(text = remoteRestaurant.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = remoteRestaurant.deliveryTime)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantScreenPreview() {
    RestaurantScreen()
}

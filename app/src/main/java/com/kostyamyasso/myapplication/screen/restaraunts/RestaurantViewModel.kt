package com.kostyamyasso.myapplication.screen.restaraunts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kostyamyasso.myapplication.data.RemoteRestaurant
import com.kostyamyasso.myapplication.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RestaurantEvent {
    data class ChooseNearestRest(val id: Int) : RestaurantEvent()
    data class ChoosePopularRest(val id: Int) : RestaurantEvent()
}

data class RestaurantState(
    val nearestRestaurants: List<RemoteRestaurant> = emptyList(),
    val popularRestaurants: List<RemoteRestaurant> = emptyList()
)


@HiltViewModel
class RestaurantViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository) :
    ViewModel() {

    private val _viewState: MutableStateFlow<RestaurantState> = MutableStateFlow(RestaurantState())
    val viewState: StateFlow<RestaurantState> = _viewState

    init {
        viewModelScope.launch {
            val response = restaurantRepository.fetchCatalog()
            _viewState.value = _viewState.value.copy(
                nearestRestaurants = response.nearest,
                popularRestaurants = response.popular
            )
        }
    }

    fun obtainEvent(event: RestaurantEvent) {
        when (event) {
            is RestaurantEvent.ChooseNearestRest -> {
                println("Chosen nearest ${event.id}")
            }
            is RestaurantEvent.ChoosePopularRest -> {
                println("Chosen popular ${event.id}")

            }
        }
    }
}
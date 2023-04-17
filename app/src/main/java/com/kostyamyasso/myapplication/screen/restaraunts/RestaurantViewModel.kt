package com.kostyamyasso.myapplication.screen.restaraunts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kostyamyasso.myapplication.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RestaurantEvent {
}

data class RestaurantState(
    val nearestRestaurants: List<RemoteRestaurant> = emptyList(),
    val popularRestaurants: List<RemoteRestaurant> = emptyList()
)


@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantDao: RestaurantDao
) :
    ViewModel() {

    private val _viewState: MutableStateFlow<RestaurantState> = MutableStateFlow(RestaurantState())
    val viewState: StateFlow<RestaurantState> = _viewState

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val databaseNearestRest = restaurantDao.getAllNearest()
            val databasePopularRest = restaurantDao.getAllPopular()
            println("fetched from database")
            if (databaseNearestRest.isNotEmpty() && databasePopularRest.isNotEmpty()) {
                println("short way")
                _viewState.value =
                    _viewState.value.copy(
                        nearestRestaurants = databaseNearestRest.map { it.mapToRemoteRestaurant() },
                        popularRestaurants = databasePopularRest.map { it.mapToRemoteRestaurant() }
                    )
            } else {
                println("long wayy")
                val response = restaurantRepository.fetchCatalog()
                _viewState.value = _viewState.value.copy(
                    nearestRestaurants = response.nearest,
                    popularRestaurants = response.popular
                )
                restaurantDao.insertAllNearest(restaurants = response.nearest.map { it.mapToNearestRestaurantEntity() }
                    .toTypedArray())
                restaurantDao.insertAllPopular(restaurants = response.popular.map { it.mapToPopularRestaurantEntity() }
                    .toTypedArray())
            }
        }
    }

    fun obtainEvent(event: RestaurantEvent) {
        // Do nothing
    }
}
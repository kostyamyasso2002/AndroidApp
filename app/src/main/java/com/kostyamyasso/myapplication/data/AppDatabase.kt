package com.kostyamyasso.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NearestRestaurantEntity::class, PopularRestaurantEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}


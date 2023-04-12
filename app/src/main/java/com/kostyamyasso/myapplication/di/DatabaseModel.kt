package com.kostyamyasso.myapplication.di

import android.content.Context
import androidx.room.Room
import com.kostyamyasso.myapplication.data.AppDatabase
import com.kostyamyasso.myapplication.data.RestaurantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "food_delivery"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideRestaurantsDao(appDatabase: AppDatabase): RestaurantDao =
        appDatabase.restaurantDao()
}

package com.kostyamyasso.myapplication.data

import androidx.room.*

@Entity(tableName = "nearest_restaurants")
data class NearestRestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String
)

@Entity(tableName = "popular_restaurants")
data class PopularRestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String

)

fun RemoteRestaurant.mapToNearestRestaurantEntity(): NearestRestaurantEntity =
    NearestRestaurantEntity(id = id, name = name, logo = image, time = deliveryTime)

fun NearestRestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)

fun RemoteRestaurant.mapToPopularRestaurantEntity(): PopularRestaurantEntity =
    PopularRestaurantEntity(id = id, name = name, logo = image, time = deliveryTime)

fun PopularRestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM nearest_restaurants")
    fun getAllNearest(): List<NearestRestaurantEntity>

    @Query("SELECT * FROM popular_restaurants")
    fun getAllPopular(): List<PopularRestaurantEntity>

    @Insert
    fun insertAllNearest(vararg restaurants: NearestRestaurantEntity)

    @Insert
    fun insertAllPopular(vararg restaurants: PopularRestaurantEntity)

    @Delete
    fun delete(restaurantEntity: NearestRestaurantEntity)
}

package com.kostyamyasso.myapplication.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val httpClient: HttpClient) {

    suspend fun fetchCatalog(): CatalogResponse {
        val response = httpClient.request("http://195.2.84.138:8081/catalog") {
            method = HttpMethod.Get
        }.body<CatalogResponse>()

        return response
    }
}
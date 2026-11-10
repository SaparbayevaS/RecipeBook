package com.example.recipebook.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("filter.php")
    suspend fun filterJapaneseMeals(
        @Query("a") area: String = "Japanese"
    ): MealResponseDto

    @GET("lookup.php")
    suspend fun lookupMeal(
        @Query("i") id: String
    ): MealResponseDto
}

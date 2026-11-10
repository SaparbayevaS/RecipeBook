package com.example.recipebook.model

data class SushiRecipe(
    val id: Int,
    val name: String,
    val category: String,
    val cookingTime: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val isFavorite: Boolean = false
)

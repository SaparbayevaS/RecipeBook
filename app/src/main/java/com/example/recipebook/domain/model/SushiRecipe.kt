package com.example.recipebook.domain.model

data class SushiRecipe(
    val id: Int,
    val name: String,
    val category: String,
    val cookingTime: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val isFavorite: Boolean = false,
    val imageUrl: String? = null
)

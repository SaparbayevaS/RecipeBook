package com.example.recipebook.ui.viewmodel

import com.example.recipebook.model.SushiRecipe

data class HomeUiState(
    val allRecipes: List<SushiRecipe> = emptyList(),
    val recipes: List<SushiRecipe> = emptyList(),
    val favoriteRecipes: List<SushiRecipe> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: String = "All",
    val isLoading: Boolean = true,
    val error: String? = null,
    val isEmpty: Boolean = false
)

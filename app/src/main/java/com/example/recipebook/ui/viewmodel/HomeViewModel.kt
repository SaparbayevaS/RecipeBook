package com.example.recipebook.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.data.repository.SushiRepository
import com.example.recipebook.model.SushiRecipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SushiRepository(application)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        Log.d("HomeViewModel", "HomeViewModel initialized")
        loadRecipes()
    }

    private fun loadRecipes(simulateError: Boolean = false) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val recipes = repository.getRecipes(simulateError)

                _uiState.update {
                    it.copy(
                        allRecipes = recipes,
                        isLoading = false,
                        error = null
                    )
                }

                updateRecipes()
            } catch (exception: Exception) {
                Log.e(
                    "HomeViewModel",
                    "Failed to load recipes",
                    exception
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Something went wrong. Please try again.",
                        isEmpty = false
                    )
                }
            }
        }
    }

    private fun updateRecipes() {
        _uiState.update { currentState ->
            val filteredRecipes = currentState.allRecipes.filter { recipe ->
                val matchesSearch = recipe.name.contains(
                    currentState.searchQuery,
                    ignoreCase = true
                )

                val matchesCategory =
                    currentState.selectedCategory == "All" ||
                            recipe.category == currentState.selectedCategory

                matchesSearch && matchesCategory
            }

            currentState.copy(
                recipes = filteredRecipes,
                favoriteRecipes = currentState.allRecipes.filter {
                    it.isFavorite
                },
                isEmpty = filteredRecipes.isEmpty()
            )
        }
    }

    fun onSearchChange(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
        updateRecipes()
    }

    fun onCategorySelected(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
        updateRecipes()
    }

    fun onFavouriteClick(recipeId: Int) {
        val updatedRecipes = _uiState.value.allRecipes.map { recipe ->
            if (recipe.id == recipeId) {
                recipe.copy(isFavorite = !recipe.isFavorite)
            } else {
                recipe
            }
        }

        repository.saveRecipes(updatedRecipes)

        _uiState.update {
            it.copy(allRecipes = updatedRecipes)
        }

        updateRecipes()
    }

    fun addRecipe(
        name: String,
        category: String,
        cookingTime: String,
        description: String,
        ingredients: List<String>,
        instructions: List<String>
    ) {
        val currentRecipes = _uiState.value.allRecipes

        val newRecipe = SushiRecipe(
            id = (currentRecipes.maxOfOrNull { it.id } ?: 0) + 1,
            name = name,
            category = category,
            cookingTime = cookingTime,
            description = description,
            ingredients = ingredients,
            instructions = instructions
        )

        val updatedRecipes = currentRecipes + newRecipe

        repository.saveRecipes(updatedRecipes)

        _uiState.update {
            it.copy(allRecipes = updatedRecipes)
        }

        updateRecipes()
    }

    fun getRecipe(recipeId: Int): SushiRecipe? {
        return _uiState.value.allRecipes.find {
            it.id == recipeId
        }
    }

    fun onRetry() {
        loadRecipes()
    }

    fun simulateError() {
        loadRecipes(simulateError = true)
    }

    override fun onCleared() {
        Log.d("HomeViewModel", "HomeViewModel cleared")
        super.onCleared()
    }
}

package com.example.recipebook.domain.repository

import com.example.recipebook.domain.model.SushiRecipe

interface SushiRepository {

    suspend fun getRecipes(): List<SushiRecipe>

    fun saveRecipes(recipes: List<SushiRecipe>)
}

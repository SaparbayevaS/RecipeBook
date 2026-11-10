package com.example.recipebook.data.repository

import android.content.Context
import com.example.recipebook.data.RecipeStorage
import com.example.recipebook.domain.model.SushiRecipe
import kotlinx.coroutines.delay
import java.io.IOException

class SushiRepository(context: Context) {

    private val appContext = context.applicationContext

    suspend fun getRecipes(
        simulateError: Boolean = false
    ): List<SushiRecipe> {
        delay(1500)

        if (simulateError) {
            throw IOException("Failed to load sushi recipes")
        }

        return RecipeStorage.load(appContext)
    }

    fun saveRecipes(recipes: List<SushiRecipe>) {
        RecipeStorage.save(appContext, recipes)
    }
}

package com.example.recipebook.data.repository

import android.content.Context
import com.example.recipebook.data.RecipeStorage
import com.example.recipebook.data.remote.MealApiService
import com.example.recipebook.data.remote.RetrofitClient
import com.example.recipebook.domain.model.SushiRecipe
import com.example.recipebook.domain.repository.SushiRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class SushiRepositoryImpl(
    context: Context,
    private val api: MealApiService = RetrofitClient.api
) : SushiRepository {

    private val appContext = context.applicationContext

    override suspend fun getRecipes(): List<SushiRecipe> {
        val response = api.filterJapaneseMeals()

        val savedRecipes = RecipeStorage
            .load(appContext)
            .associateBy { it.id }

        return coroutineScope {
            response.meals.orEmpty().map { meal ->
                async {
                    val mealId = meal.id
                        ?: throw IllegalStateException("Meal ID is missing")

                    val details = api.lookupMeal(mealId)
                        .meals
                        ?.firstOrNull()
                        ?: throw IllegalStateException(
                            "Recipe details not found: $mealId"
                        )

                    val recipeId = mealId.toIntOrNull()
                        ?: (mealId.hashCode() and Int.MAX_VALUE)

                    val ingredients = listOf(
                        details.ingredient1,
                        details.ingredient2,
                        details.ingredient3,
                        details.ingredient4,
                        details.ingredient5,
                        details.ingredient6,
                        details.ingredient7,
                        details.ingredient8,
                        details.ingredient9,
                        details.ingredient10,
                        details.ingredient11,
                        details.ingredient12,
                        details.ingredient13,
                        details.ingredient14,
                        details.ingredient15,
                        details.ingredient16,
                        details.ingredient17,
                        details.ingredient18,
                        details.ingredient19,
                        details.ingredient20
                    ).filterNotNull().filter { it.isNotBlank() }

                    val instructions = details.instructions
                        ?.split("\n")
                        ?.map { it.trim() }
                        ?.filter { it.isNotBlank() }
                        .orEmpty()

                    SushiRecipe(
                        id = recipeId,
                        name = details.name ?: meal.name.orEmpty(),
                        category = details.category ?: "Japanese",
                        cookingTime = "Not specified",
                        description = details.area
                            ?.let { "Cuisine: $it" }
                            ?: "Japanese cuisine",
                        ingredients = ingredients,
                        instructions = instructions,
                        isFavorite = savedRecipes[recipeId]?.isFavorite ?: false,
                        imageUrl = details.image
                    )
                }
            }.awaitAll()
        }
    }

    override fun saveRecipes(recipes: List<SushiRecipe>) {
        RecipeStorage.save(appContext, recipes)
    }
}

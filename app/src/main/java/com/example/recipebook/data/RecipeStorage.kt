package com.example.recipebook.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import com.example.recipebook.model.SushiRecipe

object RecipeStorage {

    private const val PREFS_NAME = "sushi_book_preferences"
    private const val RECIPES_KEY = "recipes"

    fun save(context: Context, recipes: List<SushiRecipe>) {
        val jsonArray = JSONArray()

        recipes.forEach { recipe ->
            val jsonObject = JSONObject().apply {
                put("id", recipe.id)
                put("name", recipe.name)
                put("category", recipe.category)
                put("cookingTime", recipe.cookingTime)
                put("description", recipe.description)
                put("ingredients", JSONArray(recipe.ingredients))
                put("instructions", JSONArray(recipe.instructions))
                put("isFavorite", recipe.isFavorite)
            }

            jsonArray.put(jsonObject)
        }

        context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        ).edit()
            .putString(RECIPES_KEY, jsonArray.toString())
            .apply()
    }

    fun load(context: Context): List<SushiRecipe> {
        val preferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        val savedData = preferences.getString(RECIPES_KEY, null)
            ?: return SushiData.recipes

        return try {
            val jsonArray = JSONArray(savedData)

            List(jsonArray.length()) { index ->
                val jsonObject = jsonArray.getJSONObject(index)

                SushiRecipe(
                    id = jsonObject.getInt("id"),
                    name = jsonObject.getString("name"),
                    category = jsonObject.getString("category"),
                    cookingTime = jsonObject.getString("cookingTime"),
                    description = jsonObject.getString("description"),
                    ingredients = jsonObject
                        .getJSONArray("ingredients")
                        .toStringList(),
                    instructions = jsonObject
                        .getJSONArray("instructions")
                        .toStringList(),
                    isFavorite = jsonObject.getBoolean("isFavorite")
                )
            }
        } catch (exception: Exception) {
            SushiData.recipes
        }
    }

    private fun JSONArray.toStringList(): List<String> {
        return List(length()) { index ->
            getString(index)
        }
    }
}

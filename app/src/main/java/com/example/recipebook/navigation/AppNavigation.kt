package com.example.recipebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipebook.data.RecipeStorage
import com.example.recipebook.model.SushiRecipe
import com.example.recipebook.ui.screens.AddRecipeScreen
import com.example.recipebook.ui.screens.FavoritesScreen
import com.example.recipebook.ui.screens.HomeScreen
import com.example.recipebook.ui.screens.RecipeDetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val recipes = remember {
        mutableStateListOf<SushiRecipe>().apply {
            addAll(RecipeStorage.load(context))
        }
    }

    fun saveRecipes() {
        RecipeStorage.save(context, recipes)
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                recipes = recipes,
                onRecipeClick = { recipeId ->
                    navController.navigate("details/$recipeId")
                },
                onFavoritesClick = {
                    navController.navigate("favorites")
                },
                onAddRecipeClick = {
                    navController.navigate("add")
                }
            )
        }

        composable(
            route = "details/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId")
            val recipe = recipes.find { it.id == recipeId }

            if (recipe != null) {
                RecipeDetailsScreen(
                    recipe = recipe,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onFavoriteClick = {
                        val index = recipes.indexOfFirst {
                            it.id == recipe.id
                        }

                        if (index != -1) {
                            recipes[index] = recipes[index].copy(
                                isFavorite = !recipes[index].isFavorite
                            )
                            saveRecipes()
                        }
                    }
                )
            }
        }

        composable("add") {
            AddRecipeScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = { name, category, time, description,
                                ingredients, instructions ->

                    val newRecipe = SushiRecipe(
                        id = (recipes.maxOfOrNull { it.id } ?: 0) + 1,
                        name = name,
                        category = category,
                        cookingTime = time,
                        description = description,
                        ingredients = ingredients,
                        instructions = instructions
                    )

                    recipes.add(newRecipe)
                    saveRecipes()
                    navController.popBackStack()
                }
            )
        }

        composable("favorites") {
            FavoritesScreen(
                recipes = recipes,
                onBackClick = {
                    navController.popBackStack()
                },
                onRecipeClick = { recipeId ->
                    navController.navigate("details/$recipeId")
                }
            )
        }
    }
}

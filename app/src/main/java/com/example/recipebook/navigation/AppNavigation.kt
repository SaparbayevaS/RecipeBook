package com.example.recipebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipebook.ui.screens.AddRecipeScreen
import com.example.recipebook.ui.screens.FavoritesScreen
import com.example.recipebook.ui.screens.HomeScreen
import com.example.recipebook.ui.screens.RecipeDetailsScreen
import com.example.recipebook.ui.viewmodel.HomeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val homeViewModel: HomeViewModel = viewModel()
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                uiState = uiState,
                onSearchChange = homeViewModel::onSearchChange,
                onCategorySelected = homeViewModel::onCategorySelected,
                onFavouriteClick = homeViewModel::onFavouriteClick,
                onRetry = homeViewModel::onRetry,
                onTestError = homeViewModel::simulateError,
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
            val recipe = recipeId?.let {
                homeViewModel.getRecipe(it)
            }

            if (recipe != null) {
                RecipeDetailsScreen(
                    recipe = recipe,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onFavoriteClick = {
                        homeViewModel.onFavouriteClick(recipe.id)
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
                    homeViewModel.addRecipe(
                        name = name,
                        category = category,
                        cookingTime = time,
                        description = description,
                        ingredients = ingredients,
                        instructions = instructions
                    )
                    navController.popBackStack()
                }
            )
        }

        composable("favorites") {
            FavoritesScreen(
                favoriteRecipes = uiState.favoriteRecipes,
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

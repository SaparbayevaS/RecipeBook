package com.example.recipebook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipebook.data.SushiData
import com.example.recipebook.model.SushiRecipe
import com.example.recipebook.ui.theme.RecipeBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    recipes: List<SushiRecipe>,
    onBackClick: () -> Unit = {},
    onRecipeClick: (Int) -> Unit = {}
) {
    val favoriteRecipes = recipes.filter { it.isFavorite }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Favorites ♥") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text(
                            text = "←",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (favoriteRecipes.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "♡",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "No favorite recipes yet",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Tap the heart on a recipe to save it here.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoriteRecipes, key = { it.id }) { recipe ->
                    SushiRecipeCard(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe.id) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    RecipeBookTheme {
        FavoritesScreen(
            recipes = SushiData.recipes.mapIndexed { index, recipe ->
                if (index == 0) recipe.copy(isFavorite = true) else recipe
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenDarkPreview() {
    RecipeBookTheme(darkTheme = true) {
        FavoritesScreen(
            recipes = SushiData.recipes.mapIndexed { index, recipe ->
                if (index == 0) recipe.copy(isFavorite = true) else recipe
            }
        )
    }
}

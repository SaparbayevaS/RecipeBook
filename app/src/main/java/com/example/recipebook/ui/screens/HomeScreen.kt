package com.example.recipebook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipebook.R
import com.example.recipebook.data.SushiData
import com.example.recipebook.model.SushiRecipe
import com.example.recipebook.ui.components.SushiSectionTitle
import com.example.recipebook.ui.theme.RecipeBookTheme
import com.example.recipebook.ui.viewmodel.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSearchChange: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onFavouriteClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onTestError: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit,
    onAddRecipeClick: () -> Unit
) {
    val categories = listOf("All", "Rolls", "Maki", "Nigiri", "Temaki")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "SushiBook",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Your little sushi kitchen 🍣",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onFavoritesClick) {
                        Text(
                            text = "♥",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = onAddRecipeClick) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = onSearchChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    placeholder = { Text("Search sushi...") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )
            }

            item {
                Image(
                    painter = painterResource(
                        id = R.drawable.sushi_illustration
                    ),
                    contentDescription =
                        "Japanese sushi illustration with rolls and nigiri",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(170.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                SushiSectionTitle(title = "Categories")
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        CategoryChip(
                            category = category,
                            selected = uiState.selectedCategory == category,
                            onClick = {
                                onCategorySelected(category)
                            }
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Test Error",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable(onClick = onTestError),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SushiSectionTitle(title = "Popular sushi")
                    Text(
                        text = "${uiState.recipes.size} recipes",
                        modifier = Modifier.padding(end = 16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            if (uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else if (uiState.error != null) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = uiState.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = onRetry) {
                            Text("Try again")
                        }
                    }
                }
            } else if (uiState.isEmpty) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No sushi found 🍣",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            } else {
                items(
                    items = uiState.recipes,
                    key = { it.id }
                ) { recipe ->
                    SushiRecipeCard(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe.id) },
                        onFavouriteClick = {
                            onFavouriteClick(recipe.id)
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryChip(
    category: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            color = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SushiRecipeCard(
    recipe: SushiRecipe,
    onClick: () -> Unit,
    onFavouriteClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🍣",
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Spacer(modifier = Modifier.size(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⏱ ${recipe.cookingTime}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = recipe.category,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            IconButton(onClick = onFavouriteClick) {
                Text(
                    text = if (recipe.isFavorite) "♥" else "♡",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun HomeLoadingPreview() {
    RecipeBookTheme {
        HomeScreen(
            uiState = HomeUiState(isLoading = true),
            onSearchChange = {},
            onCategorySelected = {},
            onFavouriteClick = {},
            onRetry = {},
            onTestError = {},
            onRecipeClick = {},
            onFavoritesClick = {},
            onAddRecipeClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Content State")
@Composable
fun HomeContentPreview() {
    RecipeBookTheme {
        HomeScreen(
            uiState = HomeUiState(
                recipes = SushiData.recipes,
                isLoading = false
            ),
            onSearchChange = {},
            onCategorySelected = {},
            onFavouriteClick = {},
            onRetry = {},
            onTestError = {},
            onRecipeClick = {},
            onFavoritesClick = {},
            onAddRecipeClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
fun HomeEmptyPreview() {
    RecipeBookTheme {
        HomeScreen(
            uiState = HomeUiState(
                isLoading = false,
                isEmpty = true
            ),
            onSearchChange = {},
            onCategorySelected = {},
            onFavouriteClick = {},
            onRetry = {},
            onTestError = {},
            onRecipeClick = {},
            onFavoritesClick = {},
            onAddRecipeClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun HomeErrorPreview() {
    RecipeBookTheme {
        HomeScreen(
            uiState = HomeUiState(
                isLoading = false,
                error = "Something went wrong. Please try again."
            ),
            onSearchChange = {},
            onCategorySelected = {},
            onFavouriteClick = {},
            onRetry = {},
            onTestError = {},
            onRecipeClick = {},
            onFavoritesClick = {},
            onAddRecipeClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryChipPreview() {
    RecipeBookTheme {
        CategoryChip(
            category = "Rolls",
            selected = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Category Chip Dark")
@Composable
fun CategoryChipDarkPreview() {
    RecipeBookTheme(darkTheme = true) {
        CategoryChip(
            category = "Rolls",
            selected = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SushiRecipeCardPreview() {
    RecipeBookTheme {
        SushiRecipeCard(
            recipe = SushiData.recipes.first(),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Recipe Card Dark")
@Composable
fun SushiRecipeCardDarkPreview() {
    RecipeBookTheme(darkTheme = true) {
        SushiRecipeCard(
            recipe = SushiData.recipes.first(),
            onClick = {}
        )
    }
}

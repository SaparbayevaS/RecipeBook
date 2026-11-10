# SushiBook — MVVM Architecture (SIS 4)

## 1. Screens (View)

- HomeScreen — displays sushi recipes, search and categories.
- RecipeDetailsScreen — displays recipe details.
- AddRecipeScreen — allows users to add a recipe.
- FavoritesScreen — displays favourite recipes.

## 2. ViewModels

- HomeViewModel — manages the home screen state and user actions.
- RecipeDetailsViewModel — planned bonus for the details screen.

## 3. HomeUiState

All properties are immutable (val).

- recipes: List<SushiRecipe>
- searchQuery: String
- selectedCategory: String
- isLoading: Boolean
- error: String?
- isEmpty: Boolean

## 4. User Events

- onSearchChange(query: String)
- onCategorySelected(category: String)
- onFavouriteClick(recipeId: Int)
- onRetry()

Events go from the screen to the ViewModel.
The ViewModel updates UiState using copy().
The screen observes the new state and displays updated content.

## 5. Repository

SushiRepository owns recipe data.

Responsibilities:

- Load recipes.
- Add recipes.
- Update favourites.
- Save and retrieve local data.
- Simulate network delay with delay().
- Simulate errors for testing.

RecipeStorage is used for local persistence.

## 6. Architecture Diagram

```mermaid
flowchart TD
    H[HomeScreen] --> HR[HomeRoute]
    HR --> HV[HomeViewModel]
    HV <--> HS[HomeUiState]
    HV --> R[SushiRepository]
    R --> ST[RecipeStorage]
    ST --> DB[Local Storage]

    D[RecipeDetailsScreen] --> DR[RecipeDetailsRoute]
    A[AddRecipeScreen] --> AR[AddRecipeRoute]
    F[FavoritesScreen] --> FR[FavoritesRoute]

    HR -->|User events| HV
    HS -->|UI state| HR

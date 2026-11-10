# 🍣 SushiBook

SushiBook is an Android application built with Kotlin and Jetpack Compose. It allows users to explore Japanese recipes, view recipe details, save favorites, and add their own recipes.

The application has a Japanese-inspired design with soft pink, white, and red colors.

## Features

- **Browse Recipes** — load Japanese recipes from TheMealDB API.
- **Search** — find recipes by name.
- **Filter by Category** — All, Chicken, Seafood, Side, Pork, and Vegetarian.
- **Recipe Details** — view ingredients, instructions, descriptions, and images.
- **Add Recipes** — create custom recipes.
- **Favorites** — save and view favorite recipes.
- **Local Storage** — save recipes and favorites using SharedPreferences.
- **Dark Mode** — support for light and dark themes.
- **Error Handling** — display errors and allow users to retry loading.

## Screens

1. Home Screen
2. Recipe Details Screen
3. Add Recipe Screen
4. Favorites Screen

## SIS5 — API Integration

The application uses [TheMealDB API](https://www.themealdb.com/api.php).

Base URL:
`https://www.themealdb.com/api/json/v1/1/`

Endpoints:
- `filter.php?a=Japanese` — retrieves Japanese meals.
- `lookup.php?i={id}` — retrieves full recipe information.

Retrofit handles HTTP requests, and Gson converts JSON responses into DTO objects.

## Architecture

The application uses MVVM architecture.

### Domain Layer
- `SushiRecipe` — application recipe model.
- `SushiRepository` — repository interface.

### Data Layer
- `MealApiService` — API endpoints.
- `MealDto` — API response models.
- `RetrofitClient` — Retrofit configuration.
- `SushiRepositoryImpl` — loads API data and converts DTOs into domain models.
- `RecipeStorage` — saves recipes locally using SharedPreferences and JSON.

### Presentation Layer
- `HomeViewModel` — manages recipe loading and user actions.
- `HomeUiState` — immutable UI state.
- Jetpack Compose screens display state and send user actions to the ViewModel.

### Data Flow

1. The user performs an action on the screen.
2. The screen calls a ViewModel callback.
3. The ViewModel requests data from the repository.
4. The repository calls TheMealDB API using Retrofit.
5. API DTOs are converted into domain models.
6. The ViewModel updates the UI state.
7. Compose displays the updated state.

### UI States

- **Loading** — recipes are being loaded.
- **Content** — recipes are displayed.
- **Empty** — no recipes match the search or category.
- **Error** — an error message is displayed with a retry action.

## Technologies

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- MVVM
- ViewModel
- Kotlin Coroutines
- StateFlow
- Retrofit
- Gson
- TheMealDB API
- SharedPreferences
- JSON

## How to Run

1. Clone the repository:

   `git clone https://github.com/SaparbayevaS/RecipeBook.git`

2. Open the project in Android Studio.
3. Wait for Gradle Sync.
4. Start an Android emulator or connect an Android device.
5. Run the application.

An internet connection is required to load recipes.

## Minimum Requirements

- Android 7.0 (API 24) or higher.
- Android Studio with Kotlin and Jetpack Compose support.

## Author

Created as a student project for the SIS course.

GitHub: https://github.com/SaparbayevaS

# 🍣 SushiBook

SushiBook is a mobile application built with Kotlin and Jetpack Compose. It helps users explore sushi recipes, save their favorites, and create their own recipes.

The application has a Japanese-inspired design with soft pink, white, and red colors.

## Features

- **Browse Recipes** — view a collection of 10 sushi recipes.
- **Search** — find recipes by name.
- **Filter by Category** — browse Rolls, Maki, Nigiri, and Temaki.
- **Recipe Details** — view descriptions, ingredients, cooking time, and instructions.
- **Add Recipes** — create your own sushi recipes.
- **Favorites** — save and view favorite recipes.
- **Local Storage** — recipes and favorites remain saved after restarting the app.
- **Dark Mode** — supports light and dark system themes.

## Screens

The application includes four main screens:

1. Home Screen
2. Recipe Details Screen
3. Add Recipe Screen
4. Favorites Screen

## Design

The initial screen designs are available in the `design/` directory.

### Home Screen

![Home Design](design/home.jpg)

### Recipe Details

![Recipe Details Design](design/recipe_details.jpg)

### Add Recipe

![Add Recipe Design](design/add_recipe.jpg)

### Favorites

![Favorites Design](design/favourites.jpg)

## Application Screenshots

Screenshots of the implemented application will be added here.

<!-- Add screenshots of all four screens in light and dark mode. -->

## SIS4 — MVVM Architecture

The application was refactored using the Model-View-ViewModel (MVVM) architecture.

### Architecture Layers

- **UI (View):** Jetpack Compose screens display data and send user actions to the ViewModel. Screens receive state and callbacks instead of managing business logic.
- **ViewModel:** `HomeViewModel` manages the screen state and handles user actions such as searching, selecting categories, and changing favorites.
- **Repository:** `SushiRepository` is responsible for loading and saving recipes. It also simulates a loading delay and can generate an error for testing.
- **Data Storage:** `RecipeStorage` uses SharedPreferences and JSON to store recipes locally.

### Main Components

- `HomeUiState` — an immutable data class containing recipes, favorites, search query, selected category, loading state, error message, and empty state.
- `HomeViewModel` — exposes a public `StateFlow<HomeUiState>` and updates the state using `copy()`.
- `SushiRepository` — provides recipe data to the ViewModel.
- `AppNavigation` — collects state using `collectAsStateWithLifecycle()` and connects screens to ViewModel actions.

### Data Flow

The application follows a unidirectional data flow:

1. The user performs an action on the screen.
2. The screen calls a callback.
3. The ViewModel processes the action.
4. The ViewModel requests data from the Repository when needed.
5. The ViewModel updates its `UiState`.
6. The UI observes the new state and updates automatically.

### Example: Favorite Button

When the user taps the favorite button:

1. `HomeScreen` calls `onFavouriteClick`.
2. `HomeViewModel` finds the selected recipe and creates an updated copy.
3. The Repository saves the updated recipes.
4. The ViewModel updates `HomeUiState`.
5. Compose displays the updated favorite status.

### UI States

The Home screen supports four main states:

- **Loading:** recipes are being loaded.
- **Content:** recipes are displayed.
- **Empty:** no recipes match the current search or category.
- **Error:** a loading error is displayed with a retry action.

### Configuration Changes

The `HomeViewModel` is obtained through the ViewModel system and survives Activity recreation caused by screen rotation.

Therefore, the search query, selected category, and favorite state remain available after rotation.

Recipes and favorites are also saved locally using SharedPreferences, so they remain stored after restarting the application.

## Technologies

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Navigation Compose
- ViewModel
- Kotlin Coroutines
- StateFlow
- SharedPreferences
- JSON

## Project Structure

```text
RecipeBook/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/recipebook/
│           │   ├── data/
│           │   │   ├── repository/
│           │   │   │   └── SushiRepository.kt
│           │   │   ├── SushiData.kt
│           │   │   └── RecipeStorage.kt
│           │   ├── model/
│           │   │   └── SushiRecipe.kt
│           │   ├── navigation/
│           │   │   └── AppNavigation.kt
│           │   ├── ui/
│           │   │   ├── screens/
│           │   │   ├── viewmodel/
│           │   │   │   ├── HomeUiState.kt
│           │   │   │   └── HomeViewModel.kt
│           │   │   ├── components/
│           │   │   └── theme/
│           │   └── MainActivity.kt
│           └── res/
├── design/
│   └── mvvm_architecture.md
├── README.md
└── AI_USAGE.md
```

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/SaparbayevaS/RecipeBook.git
   ```

2. Open the project in Android Studio.
3. Wait for Gradle Sync to finish.
4. Start an Android emulator or connect an Android device.
5. Run the application.

## Minimum Requirements

- Android 7.0 (API 24) or higher.
- Android Studio with Kotlin and Jetpack Compose support.

## Author

Created as a student project for the SIS course.

GitHub: https://github.com/SaparbayevaS

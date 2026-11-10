# AI Usage Report — SushiBook

## 1. AI Tools Used

### ChatGPT

ChatGPT was used as a learning and development assistant during SIS3 and SIS4.

It helped with:

- Understanding assignment requirements.
- Setting up the Android project structure.
- Writing and explaining Kotlin and Jetpack Compose code.
- Implementing navigation between screens.
- Creating reusable Composable functions.
- Implementing local data storage.
- Refactoring the application using MVVM architecture.
- Understanding Repository, ViewModel, StateFlow, and coroutines.
- Debugging compilation and runtime issues.
- Reviewing project documentation.

### Claude

Claude was used to help create the initial visual design concepts for the application.

It helped generate design ideas for:

- Home Screen
- Recipe Details Screen
- Add Recipe Screen
- Favorites Screen

The generated designs were saved in the `design/` directory.

## 2. Useful Prompts

Examples of prompts used during development:

**Prompt 1 — UI Design**

"Create a mobile sushi recipe application design called SushiBook. Use a Japanese aesthetic with soft pink, white, and red colors. Include Home, Recipe Details, Add Recipe, and Favorites screens."

**Prompt 2 — Jetpack Compose**

"Help me implement a mobile application using Kotlin and Jetpack Compose. Create a Home Screen with a LazyColumn, a LazyRow for categories, and reusable Composable components."

**Prompt 3 — Local Storage**

"Help me save custom recipes and favorite status locally on Android so that the data remains available after the application is restarted."

**Prompt 4 — MVVM Architecture**

"Help me refactor my existing Jetpack Compose application using MVVM architecture. Add a Repository, ViewModel, immutable UiState, StateFlow, loading and error states."

## 3. AI Error and How It Was Fixed

During SIS3 development, the initial project README listed Edit Recipes and Delete Recipes as planned features. However, these features were not implemented in the application.

This inconsistency was identified during the documentation review. The README was updated to describe only the implemented features.

During SIS4 development, an important architectural improvement was made.

One possible incorrect approach was to put recipe search and category filtering logic directly inside a Composable screen.

This would mix UI responsibilities with business logic.

The logic was moved to `HomeViewModel`. The ViewModel now handles search, category filtering, and favorite updates. The `HomeScreen` receives an immutable `HomeUiState` and callback functions.

This makes the UI more independent and follows the MVVM architecture.

## 4. Work Completed Manually

The student:

- Created and managed the Android project.
- Ran Gradle builds and checked compilation results.
- Tested the application in the Android emulator.
- Checked navigation between screens.
- Tested adding recipes and saving favorites.
- Checked local data persistence.
- Checked light and dark themes.
- Tested the loading and error states.
- Tested the retry action after a simulated repository error.
- Managed project files and Git commits.

AI-generated suggestions and code were reviewed and tested during development.

## 5. Reflection

AI tools helped speed up development and explain unfamiliar parts of Kotlin, Jetpack Compose, and MVVM architecture.

However, generated code needed to be reviewed, integrated, and tested.

The final application was tested in Android Studio to check that its features and architecture worked as expected.

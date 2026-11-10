# AI Usage Report — SushiBook

## 1. AI Tools Used

### ChatGPT
ChatGPT was used as a learning and development assistant during the project.

It helped with:
- Understanding the SIS3 requirements.
- Setting up the project structure.
- Writing and explaining Kotlin and Jetpack Compose code.
- Implementing navigation between screens.
- Creating reusable Composable functions.
- Implementing local data storage.
- Debugging compilation issues.
- Reviewing the project documentation.

### Claude
Claude was used to help create the initial visual design concepts for the application.

It helped generate design ideas for:
- Home Screen
- Recipe Details Screen
- Add Recipe Screen
- Favorites Screen

The generated designs were saved in the `design/` directory.

## 2. Useful Prompts

The following are examples of prompts used during development:

**Prompt 1 — UI Design**

"Create a mobile sushi recipe application design called SushiBook. Use a Japanese aesthetic with soft pink, white, and red colors. Include Home, Recipe Details, Add Recipe, and Favorites screens."

**Prompt 2 — Jetpack Compose**

"Help me implement a mobile application using Kotlin and Jetpack Compose. Create a Home Screen with a LazyColumn, a LazyRow for categories, and reusable Composable components."

**Prompt 3 — Local Storage**

"Help me save custom recipes and favorite status locally on Android so that the data remains available after the application is restarted."

## 3. AI Error and How It Was Fixed

During development, the initial project README listed Edit Recipes and Delete Recipes as planned features.

However, these features were not implemented in the current application.

This inconsistency was identified during the final documentation review. The README was updated to describe only the features that are actually implemented.

The application was also tested after code changes to check that navigation, adding recipes, favorites, and local storage worked correctly.

## 4. Work Completed Manually

The project was developed and checked in Android Studio.

The student:
- Created and managed the Android project.
- Ran Gradle builds and checked compilation results.
- Tested the application in the Android emulator.
- Checked navigation between screens.
- Tested adding recipes and saving favorites.
- Checked data persistence after restarting the application.
- Checked light and dark themes.
- Managed project files and Git commits.

AI-generated suggestions and code were reviewed and tested during development.

## 5. Reflection

AI tools helped speed up development and explain unfamiliar parts of Jetpack Compose.

However, generated code needed to be reviewed, integrated, and tested. The final application was checked in Android Studio to make sure the implemented features worked as expected.

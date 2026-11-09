# RecipeBook

## Project Description

RecipeBook is a mobile application that helps users save and organize their favorite recipes in one place.

Many people find recipes on the internet or social media but often lose them or forget where they saved them. This app will allow users to add their own recipes, write down ingredients and cooking instructions, and easily find them later.

## Planned Features

1. View Recipes – display a list of saved recipes.
2. Add Recipes – add a recipe with its name, ingredients, and cooking instructions.
3. View Recipe Details – see all information about a selected recipe.
4. Edit Recipes – update existing recipes.
5. Delete Recipes – remove recipes from the list.
6. Search Recipes – find recipes by name.
7. Local Data Storage – save recipes on the device.

## Technologies

- Kotlin
- Android Studio
- Jetpack Compose
- Android SDK

## Project Structure

```text
RecipeBook/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/com/example/recipebook/
│       │   │   ├── MainActivity.kt
│       │   │   └── ui/theme/
│       │   │       ├── Color.kt
│       │   │       ├── Theme.kt
│       │   │       └── Type.kt
│       │   ├── res/
│       │   ├── AndroidManifest.xml
│       │   └── keepRules/
│       ├── androidTest/
│       └── test/
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
└── README.md

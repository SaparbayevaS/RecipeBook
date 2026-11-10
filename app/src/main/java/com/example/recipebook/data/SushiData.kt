package com.example.recipebook.data

import com.example.recipebook.model.SushiRecipe

object SushiData {

    val recipes = listOf(
        SushiRecipe(
            id = 1,
            name = "Philadelphia Roll",
            category = "Rolls",
            cookingTime = "30 min",
            description = "A popular roll with salmon, cream cheese and cucumber.",
            ingredients = listOf("Sushi rice", "Nori", "Salmon", "Cream cheese", "Cucumber"),
            instructions = listOf(
                "Cook the sushi rice.",
                "Place nori on a bamboo mat.",
                "Spread rice over the nori.",
                "Add salmon, cream cheese and cucumber.",
                "Roll tightly and cut into pieces."
            )
        ),
        SushiRecipe(
            id = 2,
            name = "California Roll",
            category = "Rolls",
            cookingTime = "25 min",
            description = "A classic roll with crab, avocado and cucumber.",
            ingredients = listOf("Sushi rice", "Nori", "Crab sticks", "Avocado", "Cucumber"),
            instructions = listOf(
                "Cook and cool the sushi rice.",
                "Place rice on the nori.",
                "Add crab sticks, avocado and cucumber.",
                "Roll carefully.",
                "Cut into equal pieces."
            )
        ),
        SushiRecipe(
            id = 3,
            name = "Salmon Maki",
            category = "Maki",
            cookingTime = "20 min",
            description = "Simple sushi rolls filled with fresh salmon.",
            ingredients = listOf("Sushi rice", "Nori", "Salmon"),
            instructions = listOf(
                "Prepare the sushi rice.",
                "Place nori on a bamboo mat.",
                "Spread rice over the nori.",
                "Add salmon in the center.",
                "Roll and cut into pieces."
            )
        ),
        SushiRecipe(
            id = 4,
            name = "Tuna Maki",
            category = "Maki",
            cookingTime = "20 min",
            description = "Traditional maki with tuna.",
            ingredients = listOf("Sushi rice", "Nori", "Tuna"),
            instructions = listOf(
                "Cook the sushi rice.",
                "Place rice on the nori.",
                "Add tuna in the center.",
                "Roll tightly.",
                "Cut into small pieces."
            )
        ),
        SushiRecipe(
            id = 5,
            name = "Salmon Nigiri",
            category = "Nigiri",
            cookingTime = "15 min",
            description = "Hand-shaped rice topped with salmon.",
            ingredients = listOf("Sushi rice", "Salmon", "Wasabi"),
            instructions = listOf(
                "Cook and season the rice.",
                "Wet your hands.",
                "Shape rice into small oval pieces.",
                "Add a little wasabi.",
                "Place salmon on top."
            )
        ),
        SushiRecipe(
            id = 6,
            name = "Shrimp Nigiri",
            category = "Nigiri",
            cookingTime = "20 min",
            description = "Soft rice topped with cooked shrimp.",
            ingredients = listOf("Sushi rice", "Cooked shrimp", "Wasabi"),
            instructions = listOf(
                "Prepare the sushi rice.",
                "Shape rice into small ovals.",
                "Add a little wasabi.",
                "Place cooked shrimp on top."
            )
        ),
        SushiRecipe(
            id = 7,
            name = "Dragon Roll",
            category = "Rolls",
            cookingTime = "40 min",
            description = "A colorful roll with shrimp and avocado.",
            ingredients = listOf("Sushi rice", "Nori", "Shrimp", "Avocado", "Cucumber"),
            instructions = listOf(
                "Cook the sushi rice.",
                "Prepare shrimp and cucumber.",
                "Place fillings on the nori.",
                "Roll carefully.",
                "Add avocado slices on top."
            )
        ),
        SushiRecipe(
            id = 8,
            name = "Spicy Tuna Roll",
            category = "Rolls",
            cookingTime = "30 min",
            description = "A delicious tuna roll with a spicy sauce.",
            ingredients = listOf("Sushi rice", "Nori", "Tuna", "Spicy mayonnaise"),
            instructions = listOf(
                "Prepare the sushi rice.",
                "Mix tuna with spicy mayonnaise.",
                "Place the mixture on the nori.",
                "Roll tightly.",
                "Cut into pieces."
            )
        ),
        SushiRecipe(
            id = 9,
            name = "Salmon Temaki",
            category = "Temaki",
            cookingTime = "15 min",
            description = "A hand roll filled with salmon and avocado.",
            ingredients = listOf("Nori", "Sushi rice", "Salmon", "Avocado"),
            instructions = listOf(
                "Cut nori in half.",
                "Add rice to one side.",
                "Add salmon and avocado.",
                "Roll into a cone shape."
            )
        ),
        SushiRecipe(
            id = 10,
            name = "Vegetable Temaki",
            category = "Temaki",
            cookingTime = "15 min",
            description = "A fresh hand roll with avocado and cucumber.",
            ingredients = listOf("Nori", "Sushi rice", "Avocado", "Cucumber"),
            instructions = listOf(
                "Cut nori in half.",
                "Place rice on one side.",
                "Add avocado and cucumber.",
                "Roll into a cone shape."
            )
        )
    )
}

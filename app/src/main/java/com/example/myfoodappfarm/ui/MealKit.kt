package com.example.myfoodappfarm.ui

data class MealKit(
    val name: String,
    val price: String,
    val prepTime: Int,
    val image: Int,  // keep the image resource
    // Removed profilePicture and logo
    val ingredients: List<String>,
    val addIcon: Int  // keep the add icon
)

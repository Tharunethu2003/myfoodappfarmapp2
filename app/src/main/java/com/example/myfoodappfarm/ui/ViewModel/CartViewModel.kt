package com.example.myfoodappfarm.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.myfoodappfarm.ui.MealKit

class CartViewModel : ViewModel() {
    // Using a mutable state list to manage cart items
    var cartItems = mutableStateListOf<MealKit>()

    // Add a meal kit to the cart
    fun addToCart(mealKit: MealKit) {
        cartItems.add(mealKit)
    }

    // Clear the cart
    fun clearCart() {
        cartItems.clear()
    }
}

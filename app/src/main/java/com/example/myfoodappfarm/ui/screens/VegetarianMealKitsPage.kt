package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myfoodappfarm.R
import com.example.myfoodappfarm.ui.components.Footer
import com.example.myfoodappfarm.ui.components.Navbar
import com.example.myfoodappfarm.ui.theme.DarkGradientColors
import com.example.myfoodappfarm.ui.theme.LightGradientColors

@Composable
fun VegetarianMealKitsPage(navController: NavHostController) {
    // Define vegetarian meal kits
    val vegetarianMeals = listOf(
        MealKit("Veggie Burger", "Rs. 1200.00", 10, R.drawable.veggie_burger),
        MealKit("Vegetable Stir Fry", "Rs. 1500.00", 15, R.drawable.vegetable_stir_fry),
        MealKit("Stuffed Peppers", "Rs. 500.00", 15, R.drawable.stuffed_peppers),
        MealKit("Pasta Primavera", "Rs. 800.00", 20, R.drawable.pasta_primavera),
        MealKit("Mushroom Risotto", "Rs. 900.00", 25, R.drawable.mushroom_risotto)
    )

    val darkTheme = isSystemInDarkTheme()

    // Choose gradient colors based on the theme
    val gradientColors = if (darkTheme) DarkGradientColors else LightGradientColors

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                )
            )) {
        // Navigation Bar
        Navbar(
            navController = navController,
            onAboutClick = { navController.navigate("about_us") },
            onCartClick = { navController.navigate("cart_page") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Header Image
        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = "Featured Meal",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display vegetarian meal kits
        MealKitGrid(vegetarianMeals, navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Footer
        Footer(
            onExploreClick = { /* Handle Explore Farm click */ },
            onSavedClick = { /* Handle Saved click */ },
            onContactClick = { /* Handle Contact Us click */ }
        )
    }
}

// MealKitGrid implementation with meal kit cards
@Composable
fun MealKitDetailsPage(mealKits: List<MealKit>, navController: NavHostController) {
    Column {
        mealKits.forEach { mealKit ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("meal_kit_details/${mealKit.name}") // Navigate to meal details page
                    }
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    // Meal kit image
                    Image(
                        painter = painterResource(id = mealKit.imageRes),
                        contentDescription = mealKit.name,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop // Crop image to fit the box
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Meal kit details (name and price)
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(text = mealKit.name, style = MaterialTheme.typography.bodyMedium)
                        Text(text = mealKit.price, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

// Data class for MealKit
data class MealKit(
    val name: String,
    val price: String,
    val timeInMinutes: Int,
    val imageRes: Int
)

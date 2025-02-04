package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myfoodappfarm.R
import com.example.myfoodappfarm.ui.components.Footer
import com.example.myfoodappfarm.ui.components.Navbar
import com.example.myfoodappfarm.ui.theme.DarkGradientColors
import com.example.myfoodappfarm.ui.theme.LightGradientColors

@Composable
fun BrowseMealKitsPage(navController: NavHostController) {
    val nonVegetarianMeals = listOf(
        MealKit("Lava Cake", "Rs. 650.00", 25, R.drawable.lava_cake),
        MealKit("Chicken Alfredo Pizza", "Rs. 2000.00", 30, R.drawable.alfredo_pizza),
        MealKit("Caprese Salad", "Rs. 500.00", 10, R.drawable.caprese_salad),
        MealKit("Quinoa Salad", "Rs. 450.00", 15, R.drawable.quinoa_salad),
        MealKit("Classic Pancakes", "Rs. 400.00", 20, R.drawable.classic_pancakes)
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
            )){
        Navbar(
            navController = navController,
            onAboutClick = { navController.navigate("about_us") },
            onCartClick = { navController.navigate("cart_page") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = "Featured Meal",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp)) // Rounded corners for the image
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /* Handle non-vegetarian action here, if needed */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9DBE8C)),
                shape = RoundedCornerShape(12.dp), // Rounded corners
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp), // Add elevation
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Non - Veg", color = Color.White)
            }
            Button(
                onClick = { navController.navigate("vegetarian_meal_kits") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF444444)),
                shape = RoundedCornerShape(12.dp), // Rounded corners
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp), // Add elevation
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Vegetarian", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        MealKitGrid(nonVegetarianMeals, navController)

        Spacer(modifier = Modifier.height(16.dp))

        Footer(
            onExploreClick = { /* Handle Explore Farm click */ },
            onSavedClick = { /* Handle Saved click */ },
            onContactClick = { /* Handle Contact Us click */ }
        )
    }
}

@Composable
fun MealKitGrid(mealKits: List<MealKit>, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth() // Ensure full width for the grid
    ) {
        mealKits.forEach { mealKit ->
            Card(
                shape = RoundedCornerShape(12.dp), // Slightly larger rounded corners
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Increased elevation for depth
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("meal_kit_details/${mealKit.name}")
                    }
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = mealKit.imageRes),
                        contentDescription = mealKit.name,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(text = mealKit.name, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)) // Bold meal name
                        Text(text = mealKit.price, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}


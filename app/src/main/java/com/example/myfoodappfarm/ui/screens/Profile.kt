package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myfoodappfarm.R
import com.example.myfoodappfarm.ui.components.Footer
import com.example.myfoodappfarm.ui.components.Navbar
import com.example.myfoodappfarm.ui.theme.DarkGradientColors
import com.example.myfoodappfarm.ui.theme.LightGradientColors

@Composable
fun ProfileScreen(navController: NavHostController) {
    // Green-themed gradient background
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
        // Navbar with back button functionality
        Navbar(
            navController = navController,
            onAboutClick = { navController.navigate("about_us") },
            onCartClick = { navController.navigate("cart_page") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Picture
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Name
        Text(
            text = "Kira Yamaguchi",
            fontSize = 24.sp,
            color = Color(0xFF2E4B2E) // Darker green
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Recipes Tried
        Text(
            text = "Recipes Tried | 15",
            fontSize = 18.sp,
            color = Color(0xFF4B8C4D) // Medium green
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Favorites section header
        Text(
            text = "Favorites",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF9CC29E)) // Light green background for favorites
                .padding(8.dp),
            color = Color(0xFFFFFFFF) // White text color
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display sample recipe items
        RecipeItem(recipeName = "Caprese Salad", time = "10 mins", price = "Rs. 1200.00")
        RecipeItem(recipeName = "Lava Cake", time = "5 mins", price = "Rs. 650.00")
        RecipeItem(recipeName = "Fettucine", time = "20 mins", price = "Rs. 1300.00")
        RecipeItem(recipeName = "Sri Lankan Style Rice and Curry", time = "45 mins", price = "Rs. 950.00")

        Spacer(modifier = Modifier.weight(1f)) // Push content up

        // Footer with action buttons
        Footer(
            onExploreClick = { /* Handle Explore Farm click */ },
            onSavedClick = { /* Handle Saved click */ },
            onContactClick = { /* Handle Contact Us click */ }
        )
    }
}

@Composable
fun RecipeItem(recipeName: String, time: String, price: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFE0F7E5)) // Very light green for the recipe item background
            .padding(16.dp)
    ) {
        Text(
            text = recipeName,
            fontSize = 18.sp,
            color = Color(0xFF2E4B2E) // Darker green for recipe name
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$price | $time",
            fontSize = 14.sp,
            color = Color(0xFF4B8C4D) // Medium green
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Handle feedback action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFABB88C)
        )
        ) {
            Text(text = "Add Feedback", fontSize = 14.sp)
        }
    }
}

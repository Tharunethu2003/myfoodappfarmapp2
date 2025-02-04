package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myfoodappfarm.ui.ViewModel.CartViewModel
import com.example.myfoodappfarm.ui.MealKit
import com.example.myfoodappfarm.ui.components.Footer
import com.example.myfoodappfarm.ui.components.Navbar

@Composable
fun CartPage(cartViewModel: CartViewModel, navController: NavHostController) {
    // Check if the system is in dark theme
    val darkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) Color(0xFF121212) else Color(0xFFF9F9F9)), // Dark background for dark theme
        horizontalAlignment = Alignment.Start // Align items to the start
    ) {
        // Navbar
        Navbar(
            navController = navController,
            onAboutClick = { navController.navigate("about_us") },
            onCartClick = { navController.navigate("cart_page") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = if (darkTheme) Color.White else Color.Black // Title color based on theme
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display each meal kit
        cartViewModel.cartItems.forEach { mealKit ->
            MealKitCard(mealKit, darkTheme) // Pass darkTheme to the card
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space before the button

        // Checkout Button
        Button(
            onClick = { /* Handle checkout logic */ },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9DBE8C)) // Green color for the button
        ) {
            Text("Checkout", color = Color.White) // Text color for the button
        }

        // Spacer to push footer to the bottom
        Spacer(modifier = Modifier.weight(1f)) // Takes up remaining space

        // Footer
        Footer(
            onExploreClick = { /* Handle Explore Farm click */ },
            onSavedClick = { /* Handle Saved click */ },
            onContactClick = { /* Handle Contact Us click */ }
        )
    }
}

@Composable
fun MealKitCard(mealKit: MealKit, darkTheme: Boolean) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = if (darkTheme) Color(0xFF1E1E1E) else Color.White) // Background color based on theme
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = mealKit.image),
                contentDescription = mealKit.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = mealKit.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = if (darkTheme) Color.White else Color.Black // Meal name color based on theme
                )
                Text(
                    text = mealKit.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (darkTheme) Color.LightGray else Color.Black // Price color based on theme
                )
            }
        }
    }
}

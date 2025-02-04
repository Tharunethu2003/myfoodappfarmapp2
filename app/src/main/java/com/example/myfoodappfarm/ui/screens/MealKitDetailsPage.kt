package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myfoodappfarm.ui.MealKit
import com.example.myfoodappfarm.ui.ViewModel.CartViewModel
import com.example.myfoodappfarm.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfoodappfarm.ui.components.Footer
import com.example.myfoodappfarm.ui.components.Navbar
import androidx.compose.material3.Card
import androidx.compose.ui.graphics.Brush
import com.example.myfoodappfarm.ui.animations.SlideUpAndFadeInAnimation
import com.example.myfoodappfarm.ui.theme.DarkGradientColors
import com.example.myfoodappfarm.ui.theme.LightGradientColors

@Composable
fun MealKitDetailsPage(mealKit: MealKit, navController: NavHostController, cartViewModel: CartViewModel = viewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
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
                )
        ) {
            // Navigation Bar
            Navbar(
                navController = navController,
                onAboutClick = { navController.navigate("about_us") }, // Navigate to About Us
                onCartClick = { navController.navigate("cart_page") }    // Navigate to Cart Page
            )

            Spacer(modifier = Modifier.height(8.dp)) // Reduced the spacer size

            // Meal Kit Details Card with animation
            SlideUpAndFadeInAnimation(delayMillis = 300) { modifier ->
                Card(
                    modifier = modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp) // Kept padding within the card for layout clarity
                    ) {
                        Text(
                            text = mealKit.name,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Image(
                            painter = painterResource(id = mealKit.image),
                            contentDescription = mealKit.name,
                            contentScale = ContentScale.Crop, // Ensures the image fills the square without distortion
                            modifier = Modifier
                                .size(120.dp) // Setting the image to a fixed square size of 120x120 dp
                                .padding(bottom = 16.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${mealKit.prepTime} min",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Rs. ${mealKit.price}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Ingredients:",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.Start)
                        )

                        mealKit.ingredients.forEach { ingredient ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = ingredient, style = MaterialTheme.typography.bodyLarge)
                                Image(
                                    painter = painterResource(id = mealKit.addIcon),
                                    contentDescription = "Plus Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Hardcoded Reviews Section
                        Text(
                            text = "Reviews:",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        // Example of Hardcoded Reviews
                        Column(modifier = Modifier.fillMaxWidth()) {
                            ReviewCard(
                                userName = "Krish",
                                reviewText = "Loved this meal kit! It was super easy to prepare and tasted amazing.",
                                rating = 4.5f
                            )
                            ReviewCard(
                                userName = "Senal",
                                reviewText = "Great ingredients and easy instructions. Will definitely order again.",
                                rating = 5f
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Action Buttons
                        Button(
                            onClick = {
                                cartViewModel.addToCart(mealKit)
                                navController.navigate("cart_page")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9DBE8C)) // Green button
                        ) {
                            Text(text = "Add To Cart", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9DBE8C)) // Green button
                        ) {
                            Text(text = "Back", color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Spacer to push footer down

            // Footer
            Footer(
                onExploreClick = { /* Handle Explore click */ },
                onSavedClick = { /* Handle Saved click */ },
                onContactClick = { /* Handle Contact Us click */ },
            )
        }
    }
}

@Composable
fun ReviewCard(userName: String, reviewText: String, rating: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = userName, style = MaterialTheme.typography.bodyLarge, color = Color.Black)
        Text(text = reviewText, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        RatingStars(rating = rating)
    }
}

@Composable
fun RatingStars(rating: Float) {
    Row {
        repeat(5) { index ->
            Icon(
                painter = painterResource(id = if (index < rating) R.drawable.ic_filled_star else R.drawable.ic_outline_star),
                contentDescription = "Star Rating",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
    }
}

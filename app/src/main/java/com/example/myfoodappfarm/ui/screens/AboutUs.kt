package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfoodappfarm.R
import com.example.myfoodappfarm.ui.components.Navbar
import com.example.myfoodappfarm.ui.components.Footer
@Composable
fun AboutUsScreen(navController: NavHostController) {
    // Check if the system is in dark theme
    val darkTheme = isSystemInDarkTheme()

    // Gradient background colors based on the theme
    val gradientBackground = Brush.verticalGradient(
        colors = if (darkTheme) {
            listOf(
                Color(0xFF4B4B4B), // Dark gray at the top for dark theme
                Color(0xFF1F1F1F)  // Darker gray at the bottom for dark theme
            )
        } else {
            listOf(
                Color(0xFFABB88C), // Light green at the top for light theme
                Color(0xFFFFFFFF)  // White at the bottom for light theme
            )
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Allow scrolling in case content overflows
            .background(gradientBackground)
    ) {
        // Navbar for navigation
        Navbar(
            navController = navController,
            onAboutClick = { navController.navigate("about_us") }, // Navigate to About Us (current page)
            onCartClick = { navController.navigate("cart_page") }    // Navigate to Cart Page
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title: "About Us"
        Text(
            text = "About Us",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = if (darkTheme) Color.White else MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp) // Add horizontal padding only
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add a placeholder image for visual enhancement
        Image(
            painter = painterResource(id = R.drawable.about_us_image), // Replace with actual image resource
            contentDescription = "About Us Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium) // Rounded corners for the image
                .padding(horizontal = 16.dp) // Add horizontal padding
        )

        Spacer(modifier = Modifier.height(24.dp))

        // About Us Description 1
        Text(
            text = "Welcome to Farm2Plate! We are committed to providing you with the best " +
                    "healthy meal kits tailored to your dietary preferences and lifestyle. " +
                    "Our mission is to make healthy eating fun, convenient, and accessible.",
            fontSize = 18.sp,
            color = if (darkTheme) Color.LightGray else Color.Black, // Adjust text color based on theme
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp) // Horizontal padding only
        )

        Spacer(modifier = Modifier.height(16.dp))

        // About Us Description 2
        Text(
            text = "Founded in 2024, our goal is to help you save time in the kitchen " +
                    "while enjoying nutritious and delicious meals.",
            fontSize = 16.sp,
            color = if (darkTheme) Color.LightGray else Color.Black, // Adjust text color based on theme
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 16.dp) // Horizontal padding only
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Thank you text
        Text(
            text = "Thank you for choosing Farm2Plate for your meal kit delivery needs!",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (darkTheme) Color.LightGray else MaterialTheme.colorScheme.secondary, // Adjust text color based on theme
            modifier = Modifier.padding(horizontal = 16.dp) // Horizontal padding only
        )

        // Spacer to push footer down
        Spacer(modifier = Modifier.weight(1f)) // This spacer takes up available space

        // Footer with buttons for additional actions
        Footer(
            onExploreClick = { /* Handle Explore click */ },
            onSavedClick = { /* Handle Saved click */ },
            onContactClick = { /* Handle Contact Us click */ },
        )
    }
}

@Preview
@Composable
fun AboutUsPreview() {
    // Use rememberNavController to mock the NavHostController in the preview
    val navController = rememberNavController()
    AboutUsScreen(navController = navController)
}

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
    val darkTheme = isSystemInDarkTheme()

    val gradientBackground = Brush.verticalGradient(
        colors = if (darkTheme) {
            listOf(Color(0xFF4B4B4B), Color(0xFF1F1F1F))
        } else {
            listOf(Color(0xFFABB88C), Color(0xFFFFFFFF))
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp) // Ensure space for footer
                .verticalScroll(rememberScrollState())
        ) {
            Navbar(
                navController = navController,
                onAboutClick = { navController.navigate("about_us") },
                onCartClick = { navController.navigate("cart_page") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "About Us",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = if (darkTheme) Color.White else MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.about_us_image),
                contentDescription = "About Us Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome to Farm2Plate! We are committed to providing you with the best healthy meal kits...",
                fontSize = 18.sp,
                color = if (darkTheme) Color.LightGray else Color.Black,
                lineHeight = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Founded in 2024, our goal is to help you save time in the kitchen while enjoying nutritious meals.",
                fontSize = 16.sp,
                color = if (darkTheme) Color.LightGray else Color.Black,
                lineHeight = 22.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Thank you for choosing Farm2Plate for your meal kit delivery needs!",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (darkTheme) Color.LightGray else MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Fixed Footer at Bottom
        Footer(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onExploreClick = { /* Handle Explore click */ },
            onSavedClick = { /* Handle Saved click */ },
            onContactClick = { /* Handle Contact Us click */ }
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

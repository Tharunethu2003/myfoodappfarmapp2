package com.example.myfoodappfarm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myfoodappfarm.R

@Composable
fun Navbar(
    navController: NavHostController,
    onAboutClick: () -> Unit,  // Added onAboutClick parameter for About Us button
    onCartClick: () -> Unit    // Existing onCartClick parameter for cart
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF55624C))
            // Background color for the navbar
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            // Profile picture
            Image(
                painter = painterResource(id = R.drawable.profile_picture), // Profile image resource
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // Circular profile picture
                    .background(Color.Gray) // Optional background color behind the profile picture
                    .clickable { navController.navigate("profile") }, // Navigate to profile screen
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Buttons for navigation
        Row {
            NavbarButton(text = "Recipes") {
                navController.navigate("browse_meal_kits") // Navigate to the BrowseMealKitsPage
            }
            Spacer(modifier = Modifier.width(6.dp))

            // "About Us" button with onAboutClick handler
            NavbarButton(text = "About Us") {
                onAboutClick()  // Trigger the onAboutClick function when clicked
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Cart icon
        Image(
            painter = painterResource(id = R.drawable.cart), // Cart icon resource
            contentDescription = "Cart Icon",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onCartClick), // Trigger onCartClick when the cart icon is clicked
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Logo image
        // Logo image with clickable functionality
        Image(
            painter = painterResource(id = R.drawable.logo), // Logo resource
            contentDescription = "Logo",
            modifier = Modifier
                .size(40.dp) // Adjust logo size according to design
                .clickable { navController.navigate("landing_page") }, // Navigate to the landing page on click
            contentScale = ContentScale.Fit
        )

    }
}

@Composable
fun NavbarButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD0D9C0)), // Button background color
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(text = text, fontSize = 12.sp) // Button text and font size
    }
}

@Preview
@Composable
fun NavbarPreview() {
    // Dummy NavHostController for preview purposes
    val navController = NavHostController(LocalContext.current)

    Navbar(
        navController = navController,
        onAboutClick = {}, // Pass an empty lambda for About Us preview
        onCartClick = {}   // Pass an empty lambda for Cart preview
    )
}

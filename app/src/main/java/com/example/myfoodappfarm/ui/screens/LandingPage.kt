package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.example.myfoodappfarm.ui.components.Navbar
import com.example.myfoodappfarm.ui.components.Footer
import com.example.myfoodappfarm.R
import com.example.myfoodappfarm.ui.theme.DarkGradientColors
import com.example.myfoodappfarm.ui.theme.LightGradientColors
import com.example.myfoodappfarm.ui.theme.getTextColor

@Composable
fun LandingPage(navController: NavHostController) {
    val popularRecipes = getPopularRecipes()

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
        // Navbar
        Navbar(
            navController = navController,
            onAboutClick = { navController.navigate("about_us") }, // Navigates to About Page
            onCartClick = { navController.navigate("cart_page") }    // Navigates to Cart Page
        )

        // Main image section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_image),
                    contentDescription = "Featured Meal",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        }

        // Scrollable card section (Recipe Cards)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(3) {
                RecipeCard(navController)
                RecipeCard2(navController)
                RecipeCard3(navController)
            }
        }

        // Popular Recipes Section with cards side by side
        PopularRecipesSection(recipes = popularRecipes)

        // Footer at the bottom
        Spacer(modifier = Modifier.weight(1f))

        WhyChooseUsSection()

        Spacer(modifier = Modifier.weight(1f))

        Footer(
            onExploreClick = { /* Handle Explore Farm click */ },
            onSavedClick = { /* Handle Saved click */ },
            onContactClick = { /* Handle Contact Us click */ }
        )
    }
}

@Composable
fun PopularRecipesSection(recipes: List<Recipe>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Section Title
        Text(
            text = "Popular Recipes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // LazyRow to display cards side by side
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(recipes) { recipe ->
                RecipeCardWithTime(
                    imageResource = recipe.imageResource,
                    title = recipe.title,
                    time = recipe.time
                )
            }
        }
    }
}

@Composable
fun RecipeCardWithTime(imageResource: Int, title: String, time: String) {
    Box(
        modifier = Modifier
            .width(200.dp) // Width for each recipe card
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp)) // Rounded corners
            .background(Color(0xFF74796D)) // Grayish background
            .padding(8.dp) // Padding inside the box
    ) {
        Column {
            // Image section
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title and time text
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = time,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun RecipeCard(navController: NavHostController) {
    Box(
        modifier = Modifier
            .width(359.dp) // Slightly wider for better proportions
            .height(150.dp) // Adjust height to fit better
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp)) // Rounded corners
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF74796D),  // Light grayish background
                        Color(0xFF74796D)   // Slightly darker gray at the bottom
                    )
                )
            )
            .padding(8.dp) // Applies 16.dp padding to all sides
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Image section on the left
            Image(
                painter = painterResource(id = R.drawable.image2),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .size(150.dp) // Adjust size for a compact image
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Text and Button section on the right
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Recipes to Fit Your Diet",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.LightGray
                )

                Text(
                    text = "In seconds with us.\nTailored meal plans.",
                    fontSize = 15.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Browse button
                Button(
                    onClick = { navController.navigate("browse_meal_kits") }, // Navigate to Recipes page
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(35.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFABB88C), // Custom green button color
                        contentColor = Color.White // Text color inside the button
                    )
                ) {
                    Text(text = "Browse Recipes", fontSize = 17.sp)
                }
            }
        }
    }
}

@Composable
fun RecipeCard2(navController: NavHostController) {
    Box(
        modifier = Modifier
            .width(359.dp) // Slightly wider for better proportions
            .height(150.dp) // Adjust height to fit better
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp)) // Rounded corners
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF74796D),  // Light grayish background
                        Color(0xFF74796D)   // Slightly darker gray at the bottom
                    )
                )
            )
            .padding(8.dp) // Padding inside the box
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Image section on the left
            Image(
                painter = painterResource(id = R.drawable.image5),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .size(150.dp) // Adjust size for a compact image
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Text and Button section on the right
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Recipes to Fit Your Diet",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.LightGray
                )

                Text(
                    text = "In seconds with us.\nTailored meal plans.",
                    fontSize = 15.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Browse button
                Button(
                    onClick = { navController.navigate("browse_meal_kits") }, // Navigate to Recipes page
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(35.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFABB88C), // Custom green button color
                        contentColor = Color.White // Text color inside the button
                    )
                ) {
                    Text(text = "Browse Recipes", fontSize = 17.sp)
                }
            }
        }
    }
}

@Composable
fun RecipeCard3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .width(359.dp) // Slightly wider for better proportions
            .height(150.dp) // Adjust height to fit better
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp)) // Rounded corners
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF74796D),  // Light grayish background
                        Color(0xFF74796D)   // Slightly darker gray at the bottom
                    )
                )
            )
            .padding(8.dp) // Padding inside the box
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Image section on the left
            Image(
                painter = painterResource(id = R.drawable.image6),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .size(150.dp) // Adjust size for a compact image
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Text and Button section on the right
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Recipes to Fit Your Diet",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.LightGray
                )

                Text(
                    text = "In seconds with us.\nTailored meal plans.",
                    fontSize = 15.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Browse button
                Button(
                    onClick = { navController.navigate("browse_meal_kits") }, // Navigate to Recipes page
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(35.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFABB88C), // Custom green button color
                        contentColor = Color.White // Text color inside the button
                    )
                ) {
                    Text(text = "Browse Recipes", fontSize = 17.sp)
                }
            }
        }
    }

}
@Composable
fun WhyChooseUsSection() {
    // Get the dark theme state
    val darkTheme = isSystemInDarkTheme()

    // Get the text color based on the current theme
    val textColor = getTextColor(darkTheme)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Section Title
        Text(
            text = "Why Choose Us",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp),
            color = textColor // Use the determined text color
        )

        // List of reasons
        val reasons = listOf(
            "Fresh Ingredients: We source the freshest ingredients for all our meals.",
            "Customizable Meal Plans: Tailor your meals to fit your dietary needs.",
            "Convenient Delivery: Enjoy quick and hassle-free meal delivery to your doorstep.",
            "Healthy Choices: Our recipes are designed with your health in mind."
        )

        // Display reasons
        reasons.forEach { reason ->
            Text(
                text = "• $reason",
                fontSize = 16.sp,
                color = textColor, // Use the determined text color
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}



// Example function to return a list of popular recipes
fun getPopularRecipes(): List<Recipe> {
    return listOf(
        Recipe(R.drawable.healthy_bibimbap, "Healthy Bibimbap", "30 min"),
        Recipe(R.drawable.sushi, "Sushi", "20 min"),
        Recipe(R.drawable.udon, "Udon", "25 min"),
        // Add more recipes as needed
    )
}

// Data class for Recipe
data class Recipe(val imageResource: Int, val title: String, val time: String)

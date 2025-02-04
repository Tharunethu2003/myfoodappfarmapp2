package com.example.myfoodappfarm.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.myfoodappfarm.ui.screens.*
import com.example.myfoodappfarm.R
import com.example.myfoodappfarm.ui.MealKit
import com.example.myfoodappfarm.ui.ViewModel.CartViewModel

val nonVegetarianMeals = listOf(
    MealKit("Lava Cake", "Rs. 650.00", 25, R.drawable.lava_cake, listOf("Chocolate", "Flour", "Sugar"), R.drawable.icon_plus),
    MealKit("Chicken Alfredo Pizza", "Rs. 2000.00", 30, R.drawable.alfredo_pizza, listOf("Chicken", "Cheese", "Sauce"), R.drawable.icon_plus),
    MealKit("Caprese Salad", "Rs. 500.00", 10, R.drawable.caprese_salad, listOf("Tomato", "Mozzarella", "Basil"), R.drawable.icon_plus),
    MealKit("Quinoa Salad", "Rs. 450.00", 15, R.drawable.quinoa_salad, listOf("Quinoa", "Veggies"), R.drawable.icon_plus),
    MealKit("Classic Pancakes", "Rs. 400.00", 20, R.drawable.classic_pancakes, listOf("Flour", "Milk", "Eggs"), R.drawable.icon_plus)
)

val vegetarianMeals = listOf(
    MealKit("Veggie Burger", "Rs. 1200.00", 10, R.drawable.veggie_burger, listOf("Veggie Patty", "Bun", "Lettuce"), R.drawable.icon_plus),
    MealKit("Vegetable Stir Fry", "Rs. 1500.00", 15, R.drawable.vegetable_stir_fry, listOf("Mixed Veggies", "Sauce"), R.drawable.icon_plus),
    MealKit("Stuffed Peppers", "Rs. 500.00", 15, R.drawable.stuffed_peppers, listOf("Peppers", "Rice", "Cheese"), R.drawable.icon_plus),
    MealKit("Pasta Primavera", "Rs. 800.00", 20, R.drawable.pasta_primavera, listOf("Pasta", "Veggies"), R.drawable.icon_plus),
    MealKit("Mushroom Risotto", "Rs. 900.00", 25, R.drawable.mushroom_risotto, listOf("Rice", "Mushrooms", "Broth"), R.drawable.icon_plus)
)


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val cartViewModel: CartViewModel = viewModel() // Create the ViewModel instance here

    NavHost(navController = navController, startDestination = "first_page", modifier = modifier) {
        composable("first_page") {
            FirstPage(
                onNavigateToLoginPage = { navController.navigate("login_page") },
                onNavigateToRegisterPage = { navController.navigate("register_page") }
            )
        }
        composable("login_page") {
            LoginPage(onLoginClick = { navController.navigate("landing_page") })
        }
        composable("register_page") {
            RegisterPage(onRegisterClick = { navController.navigate("login_page") })
        }
        composable("landing_page") { LandingPage(navController) }
        composable("browse_meal_kits") { BrowseMealKitsPage(navController) }
        composable("vegetarian_meal_kits") { VegetarianMealKitsPage(navController) }
        composable("profile") { ProfileScreen(navController) }

        // Pass navController to AboutUsScreen
        composable("about_us") { AboutUsScreen(navController) }

        // Pass navController to CartPage
        composable("cart_page") {
            CartPage(cartViewModel = cartViewModel, navController = navController)
        }

        composable(
            "meal_kit_details/{mealKitName}",
            arguments = listOf(navArgument("mealKitName") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealKitName = backStackEntry.arguments?.getString("mealKitName") ?: ""
            val selectedMealKit = nonVegetarianMeals.find { it.name == mealKitName }
                ?: vegetarianMeals.find { it.name == mealKitName }

            if (selectedMealKit != null) {
                MealKitDetailsPage(mealKit = selectedMealKit, navController = navController, cartViewModel = cartViewModel) // Pass the ViewModel here
            }
        }
    }
}


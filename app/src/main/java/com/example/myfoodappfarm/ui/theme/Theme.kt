package com.example.myfoodappfarm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import com.example.myfoodappfarm.ui.screens.FirstPage

// Define color palettes
val LightColorPalette = lightColorScheme(
    primary = Color(0xFF446732),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC5EFAB),
    onPrimaryContainer = Color(0xFF072100),
    secondary = Color(0xFF55624C),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD9E7CB),
    onSecondaryContainer = Color(0xFF131F0D),
    tertiary = Color(0xFF386666),
    onSurface = Color(0x921D1F1C),
    surfaceVariant = Color(0xFFE0E4D6),
    onSurfaceVariant = Color(0xFF43483E),
    outline = Color(0xFF74796D)
)

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFAAD291),
    onPrimary = Color(0xFF191D16),
    primaryContainer = Color(0xFF072100),
    onPrimaryContainer = Color(0xFFC5EFAB),
    secondary = Color(0xFF43483E),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF131F0D),
    onSecondaryContainer = Color(0xFFD9E7CB),
    tertiary = Color(0xFF3D7878),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF2E312B),
    onSurfaceVariant = Color(0xFFE0E4D6),
    outline = Color(0xFFC3C8BB)
)

// Button color definitions
val ButtonColors = mapOf(
    "light" to Pair(Color(0xFFDDDDDD), Color(0xFF000000)), // (background, content) for light mode
    "dark" to Pair(Color(0xFF444444), Color(0xFFFFFFFF))  // (background, content) for dark mode
)

val LightGradientColors = listOf(
    Color(0xFFABB88C), // Light green
    Color(0xFFFFFFFF)   // White
)

val DarkGradientColors = listOf(
    Color(0xFF20251D), // Darker color for dark mode
    Color(0xFF2E312B)  // Another darker color for dark mode
)



@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Composable
fun MyApp() {
    AppTheme {
        FirstPage(
            onNavigateToLoginPage = { /* Navigate to Login Page */ },
            onNavigateToRegisterPage = { /* Navigate to Register Page */ }
        )
    }
}

fun getTextColor(isDarkTheme: Boolean): Color {
    return if (isDarkTheme) {
        Color.White // Dark theme text color
    } else {
        Color.Black // Light theme text color
    }
}

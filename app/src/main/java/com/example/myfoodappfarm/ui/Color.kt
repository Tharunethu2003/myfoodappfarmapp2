package com.example.myfoodappfarm.ui


import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF446732), // Your primary light color
    onPrimary = Color(0xFFFFFFFF), // Your onPrimary light color
    primaryContainer = Color(0xFFC5EFAB),
    onPrimaryContainer = Color(0xFF072100),
    secondary = Color(0xFF55624C),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD9E7CB),
    onSecondaryContainer = Color(0xFF131F0D),
    tertiary = Color(0xFF386666),
    onSurface = Color(0xFF191D16),
    surfaceVariant = Color(0xFFE0E4D6),
    onSurfaceVariant = Color(0xFF43483E),
    outline = Color(0xFF74796D),
    // Add other colors as needed
)

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFAAD291), // Your primary dark color
    onPrimary = Color(0xFF191D16), // Your onPrimary dark color
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
    outline = Color(0xFFC3C8BB),
    // Add other colors as needed
)

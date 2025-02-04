package com.example.myfoodappfarm.ui.animations

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.runtime.*


@Composable
fun FadeInAnimation(content: @Composable (Modifier) -> Unit) {
    // State to control the alpha value (opacity) of the content
    val visible = remember { mutableStateOf(false) }

    // Animate the alpha (opacity) value based on the visibility state
    val alpha = animateFloatAsState(targetValue = if (visible.value) 1f else 0f)

    // Trigger the visibility state change (fade-in)
    LaunchedEffect(Unit) {
        visible.value = true
    }

    // Apply the animated modifier to the content
    content(Modifier.alpha(alpha.value))
}


@Composable
fun SlideUpAndFadeInAnimation(
    initialOffsetY: Dp = 100.dp, // Start offset
    durationMillis: Int = 2000, // Animation duration
    delayMillis: Int = 0, // Optional delay before animation starts
    content: @Composable (Modifier) -> Unit
) {
    // Track offset and opacity state
    var offsetY by remember { mutableStateOf(initialOffsetY) }
    var alpha by remember { mutableStateOf(0f) }

    // Animate offset and opacity values
    val animatedOffsetY by animateDpAsState(targetValue = offsetY)
    val animatedAlpha by animateFloatAsState(targetValue = alpha)

    // Launch the animation with an optional delay
    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        offsetY = 0.dp // Slide up to the original position
        alpha = 1f // Fade in
    }

    // Apply the animated offset and alpha to the content
    content(Modifier.offset(y = animatedOffsetY).alpha(animatedAlpha))
}
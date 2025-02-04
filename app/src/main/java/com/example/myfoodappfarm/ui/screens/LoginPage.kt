
package com.example.myfoodappfarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfoodappfarm.ui.theme.ButtonColors

@Composable
fun LoginPage(onLoginClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Load background image
    val backgroundImage: Painter = painterResource(id = com.example.myfoodappfarm.R.drawable.image6)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = backgroundImage,
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Overlay for better readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
        )

        // Login Form Box in a Card
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center)
                .padding(16.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Login", style = MaterialTheme.typography.titleLarge, fontSize = 24.sp)

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.small)
                        .padding(16.dp),
                    decorationBox = { innerTextField ->
                        if (username.isEmpty()) {
                            Text("Username", color = MaterialTheme.colorScheme.onSecondaryContainer)
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.small)
                        .padding(16.dp),
                    decorationBox = { innerTextField ->
                        if (password.isEmpty()) {
                            Text("Password", color = MaterialTheme.colorScheme.onSecondaryContainer)
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Use button colors from the theme
                val buttonColor = if (isSystemInDarkTheme()) ButtonColors["dark"] else ButtonColors["light"]

                Button(
                    onClick = {
                        onLoginClick()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor?.first ?: Color.Gray) // Use the button background color
                ) {
                    Text("Login", color = buttonColor?.second ?: Color.White) // Use the button text color
                }
            }
        }
    }
}

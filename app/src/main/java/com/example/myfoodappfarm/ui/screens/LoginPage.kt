package com.example.myfoodappfarm.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginPage(onLoginSuccess: () -> Unit, onRegisterClick: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    // Access the context for Toast messages
    val context = LocalContext.current

    // Handle login click
    fun handleLogin() {
        auth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onLoginSuccess() // Navigate to landing page
                } else {
                    // Show error message
                    Toast.makeText(
                        context,
                        "Login Failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Email field
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Password field
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Login Button
            Button(onClick = { handleLogin() }) {
                Text("Login")
            }

            // Register Text Link
            Spacer(modifier = Modifier.height(16.dp)) // Add some space before the Register text
            Text(
                text = "Don't have an account? Register here.",
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}

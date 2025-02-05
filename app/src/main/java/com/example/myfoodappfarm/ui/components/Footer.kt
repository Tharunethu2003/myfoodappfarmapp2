package com.example.myfoodappfarm.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.example.myfoodappfarm.R // replace with your actual package name

@Composable
fun Footer(
    onExploreClick: () -> Unit,
    onSavedClick: () -> Unit,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier // Add the modifier parameter with a default value
) {
    Row(
        modifier = modifier
            .background(Color(0xFFABB88C)) // light green background
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FooterItem(
            icon = R.drawable.location, // Replace with your actual icon
            text = "Explore Farm",
            onClick = onExploreClick
        )
        FooterItem(
            icon = R.drawable.saved, // Replace with appropriate icon
            text = "Saved",
            onClick = onSavedClick
        )
        FooterItem(
            icon = R.drawable.contact_us, // Replace with appropriate icon
            text = "Contact Us",
            onClick = onContactClick
        )
    }
}

@Composable
fun FooterItem(icon: Int, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(25.dp), // Icon size reduced to 36.dp
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun FooterLinks() {
    val context = LocalContext.current

    Footer(
        onExploreClick = {
            // Open Google Maps link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/sdSkam8vgruJg7YP9"))
            context.startActivity(intent)
        },
        onSavedClick = {
            // Navigate to "Saved" page (for example)
            // Replace with your actual navigation logic (this could use NavController)
            println("Navigating to Saved page...")
        },
        onContactClick = {
            // Open WhatsApp chat link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/0772174723"))
            context.startActivity(intent)
        }
    )
}

@Preview
@Composable
fun FooterPreview() {
    FooterLinks()
}

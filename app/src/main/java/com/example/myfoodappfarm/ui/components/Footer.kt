package com.example.myfoodappfarm.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
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
import com.example.myfoodappfarm.R // replace with your actual package name



@Composable
fun Footer(
    onExploreClick: () -> Unit,
    onSavedClick: () -> Unit,
    onContactClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFABB88C)) // light green background
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FooterItem(
            icon = R.drawable.location, // Assuming you uploaded your icon to res/drawable
            text = "Explore Farm",
            onClick = onExploreClick
        )
        FooterItem(
            icon = R.drawable.saved, // Replace with appropriate resource
            text = "Saved",
            onClick = onSavedClick
        )
        FooterItem(
            icon = R.drawable.contact_us, // Replace with appropriate resource
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




@Preview
@Composable
fun FooterPreview() {
    Footer(
        onExploreClick = {},
        onSavedClick = {},
        onContactClick = {}
    )
}

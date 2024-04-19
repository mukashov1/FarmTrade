package com.example.farmtrade.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.farmtrade.R

@Composable
fun AboutScreen(navController: NavController) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center // Center the content horizontally
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "backArrow",
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(
                text = "About Us",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .fillMaxWidth()
            )
        }

        val items = listOf(
            AboutItem.TextItem("Version 1.0"),
            AboutItem.TextItem("Ads: No"),
            AboutItem.GroupItem("Connect with us"),
            AboutItem.LinkItem("Email", "elmehdi.sakout@gmail.com"),
            AboutItem.LinkItem("Website", "https://mehdisakout.com/"),
            // Add other links...
        )

        AboutPage(
            isRTL = false,
            customFont = "YourCustomFont.ttf",
            image = R.drawable.ic_launcher_background, // Replace with actual image bitmap
            items = items
        )

    }
}

@Composable
fun AboutPage(
    isRTL: Boolean = false,
    customFont: String? = null,
    image: Int = 0,
    items: List<AboutItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        image?.let {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background), // Replace with actual image resource
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
            )
        }

        items.forEach { item ->
            when (item) {
                is AboutItem.TextItem -> {
                    Text(
                        text = item.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                is AboutItem.LinkItem -> {
                    Text(
                        text = item.label,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.clickable { /* Handle link click */ }
                    )
                }
                is AboutItem.GroupItem -> {
                    Text(
                        text = item.label,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}

sealed class AboutItem {
    data class TextItem(val text: String) : AboutItem()
    data class LinkItem(val label: String, val url: String) : AboutItem()
    data class GroupItem(val label: String) : AboutItem()
}
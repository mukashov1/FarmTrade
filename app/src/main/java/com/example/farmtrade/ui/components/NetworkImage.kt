package com.example.farmtrade.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun NetworkImage(url: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )
    Image(
        painter = painter,
        contentDescription = "Product Image",
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop
    )
}
package com.example.farmtrade.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun MyOrdersScreen() {
    Column {
        Row {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "BackArrow")
            Text(text = "My Orders")
        }
        Divider()
        Card {
            Row {
                Text(text = "Order ID")
                Text(text = "54545")
            }
        }
    }
}
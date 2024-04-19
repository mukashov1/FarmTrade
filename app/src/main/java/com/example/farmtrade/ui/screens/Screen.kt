package com.example.farmtrade.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.example.farmtrade.R

sealed class Screen(val route: String, val icon: @Composable () -> Painter, val title: String) {
    object Catalog : Screen("catalog", { painterResource(R.drawable.home) }, "Catalog")
    object Saved : Screen("saved", { painterResource(R.drawable.saved) }, "Favorite")
    object Create : Screen("create", { rememberVectorPainter(image = Icons.Rounded.Add) }, "Create")
    object Basket : Screen("cart", { painterResource(R.drawable.bag) }, "Cart")
    object Profile : Screen("profile", { painterResource(R.drawable.account) }, "Profile")
}

val bottomNavigationItems =  listOf(
    Screen.Catalog,
    Screen.Saved,
    Screen.Create,
    Screen.Basket,
    Screen.Profile
)

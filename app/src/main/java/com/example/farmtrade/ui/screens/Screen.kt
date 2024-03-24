package com.example.farmtrade.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.farmtrade.R

sealed class Screen(val route: String, val icon: @Composable () -> Painter, val title: String) {
    object Home : Screen("home", { painterResource(R.drawable.home) }, "Главная")
    object Catalog : Screen("catalog", { painterResource(R.drawable.saved) }, "Каталог")
    object Basket : Screen("cart", { painterResource(R.drawable.bag) }, "Корзина")
    object Offers : Screen("offers", { painterResource(R.drawable.chat) }, "Акции")
    object Profile : Screen("profile", { painterResource(R.drawable.account) }, "Профиль")
}

val bottomNavigationItems =  listOf(
    Screen.Home,
    Screen.Catalog,
    Screen.Basket,
    Screen.Offers,
    Screen.Profile
)

package com.example.farmtrade.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.farmtrade.R

sealed class Screen(val route: String, val icon: @Composable () -> Painter, val title: String) {
    object Catalog : Screen("catalog", { painterResource(R.drawable.home) }, "Каталог")
    object Saved : Screen("saved", { painterResource(R.drawable.saved) }, "Сохраненный")
    object Basket : Screen("cart", { painterResource(R.drawable.bag) }, "Корзина")
    object Offers : Screen("offers", { painterResource(R.drawable.chat) }, "Акции")
    object Profile : Screen("profile", { painterResource(R.drawable.account) }, "Профиль")
}

val bottomNavigationItems =  listOf(
    Screen.Catalog,
    Screen.Saved,
    Screen.Basket,
    Screen.Offers,
    Screen.Profile
)

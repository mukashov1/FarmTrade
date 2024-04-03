package com.example.farmtrade.ui

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.farmtrade.R
import com.example.farmtrade.data.repository.DataStoreRepository
import com.example.farmtrade.ui.screens.BasketScreen
import com.example.farmtrade.ui.screens.CatalogScreen
import com.example.farmtrade.ui.screens.LogInScreen
import com.example.farmtrade.ui.screens.OffersScreen
import com.example.farmtrade.ui.screens.ProductScreen
import com.example.farmtrade.ui.screens.ProfileScreen
import com.example.farmtrade.ui.screens.SavedScreen
import com.example.farmtrade.ui.screens.Screen
import com.example.farmtrade.ui.screens.bottomNavigationItems
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        installSplashScreen()


        val dataStoreRepository = DataStoreRepository(applicationContext)

        setContent {
            val navController = rememberNavController()

            // Create a MutableState to hold the login state
            var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }


            LaunchedEffect(key1 = Unit) {
                // Collect the login state once and update isLoggedIn
                isLoggedIn = dataStoreRepository.isLoggedIn.first()
            }


            if (isLoggedIn == null) {
                // Optional: Display a progress indicator while loading the login state
                CircularProgressIndicator()
            } else {
                // Now isLoggedIn is not null, you can decide which screen to display
                if (isLoggedIn == true) {
                    AppBottomNavigation(navController = navController)
                } else {
                    LogInScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppBottomNavigation(navController: NavController) {
    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .drawBehind {

                        val strokeWidth = 1 * density
                        val y = size.height + strokeWidth / 2

                        drawLine(
                            Color.Blue,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
            ) {
                BottomNavigation(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    bottomNavigationItems.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    screen.icon(),
                                    modifier = Modifier.size(24.dp),
                                    // Устанавливаем цвет иконки в зависимости от выбранного состояния
                                    tint = if (currentRoute == screen.route) colorResource(id = R.color.pink) else Color.Black,
                                    contentDescription = screen.title,
                                )
                            },
                            alwaysShowLabel = false,
                            selected = currentRoute == screen.route,
                            onClick = {
                                // Переход к выбранному маршруту с оптимизацией поведения стека
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            },
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                            // Применяем розовый цвет только к тексту выбранного элемента
                            selectedContentColor = colorResource(id = R.color.pink),
                            unselectedContentColor = Color.Black
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController as NavHostController,
            startDestination = Screen.Saved.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Catalog.route) { CatalogScreen(navController) }
            composable(Screen.Saved.route) { SavedScreen() }
            composable(Screen.Basket.route) { BasketScreen() }
            composable(Screen.Offers.route) { OffersScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable("loginScreen") { LogInScreen(navController) }
            composable("productScreen/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                productId?.let {
                    ProductScreen(navController, productId = it)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
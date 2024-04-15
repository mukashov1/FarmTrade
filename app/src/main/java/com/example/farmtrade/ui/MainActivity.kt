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
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
import com.example.farmtrade.ui.screens.RegistrationScreen
import com.example.farmtrade.ui.screens.SavedScreen
import com.example.farmtrade.ui.screens.Screen
import com.example.farmtrade.ui.screens.bottomNavigationItems
import com.google.firebase.Firebase
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        installSplashScreen()

        val dataStoreRepository = DataStoreRepository(applicationContext)
        Firebase.initialize(this)

        setContent {
            val navController = rememberNavController()
            val viewModel = MainActivityViewModel(navController)
            viewModel.getUserData()
            viewModel.checkForActiveSession()
            // Create a MutableState to hold the login state
            val isLoggedIn = viewModel.isUserLoggedIn.value
            //            RegistrationScreen(navController = navController)

            val startDestination: NavDestination = NavDestination(navigatorName = Screen.Catalog.route)

            AppBottomNavigation(navController = navController, startDestination = startDestination)
        }
    }
}

@Composable
fun AppBottomNavigation(navController: NavController, startDestination: NavDestination) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // List of routes where the bottom navigation should be hidden
    val routesWithHiddenBottomNav = listOf("loginScreen", "registrationScreen")

    Scaffold(
        bottomBar = {
            if (!routesWithHiddenBottomNav.contains(currentRoute)) {
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
                        bottomNavigationItems.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        screen.icon(),
                                        modifier = Modifier.size(24.dp),
                                        tint = if (currentRoute == screen.route) colorResource(id = R.color.pink) else Color.Black,
                                        contentDescription = screen.title,
                                    )
                                },
                                alwaysShowLabel = false,
                                selected = currentRoute == screen.route,
                                onClick = {
                                    if (currentRoute != screen.route) {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                        }
                                    }
                                },
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                                selectedContentColor = colorResource(id = R.color.pink),
                                unselectedContentColor = Color.Black
                            )
                        }
                    }
                }

            }
        }
    ) {
        NavHost(
            navController as NavHostController,
            startDestination = startDestination.navigatorName,
            modifier = Modifier.padding(it)
        ) {
            composable("loginScreen") { LogInScreen(navController) }
            composable("registrationScreen") { RegistrationScreen(navController) }
            composable(Screen.Catalog.route) { CatalogScreen(navController) }
            composable(Screen.Saved.route) { SavedScreen() }
            composable(Screen.Basket.route) { BasketScreen() }
            composable(Screen.Offers.route) { OffersScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
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
package com.example.farmtrade.ui

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.farmtrade.data.repository.DataStoreRepository
import com.example.farmtrade.ui.screens.AboutScreen
import com.example.farmtrade.ui.screens.AddressScreen
import com.example.farmtrade.ui.screens.BasketScreen
import com.example.farmtrade.ui.screens.CatalogScreen
import com.example.farmtrade.ui.screens.ChatScreen
import com.example.farmtrade.ui.screens.LogInScreen
import com.example.farmtrade.ui.screens.MyOrdersScreen
import com.example.farmtrade.ui.screens.NewProductScreen
import com.example.farmtrade.ui.screens.ProductScreen
import com.example.farmtrade.ui.screens.ProfileScreen
import com.example.farmtrade.ui.screens.RegistrationScreen
import com.example.farmtrade.ui.screens.SavedScreen
import com.example.farmtrade.ui.screens.Screen
import com.example.farmtrade.ui.screens.SettingsScreen
import com.example.farmtrade.ui.screens.bottomNavigationItems
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        installSplashScreen()

        val dataStoreRepository = DataStoreRepository(applicationContext)
        Firebase.initialize(this)

        val db = FirebaseFirestore.getInstance()
        setContent {
            val navController = rememberNavController()
            val viewModel = MainActivityViewModel(navController)
            viewModel.getUserData()
            viewModel.checkForActiveSession()
            val isLoggedIn = viewModel.isUserLoggedIn.value

            val startDestination: NavDestination =
                if (isLoggedIn == true) NavDestination(navigatorName = Screen.Create.route)
                else NavDestination(
                    navigatorName = "registrationScreen"
                )

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
                BottomNavigation(
                    backgroundColor = Color.White,
                    contentColor = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    bottomNavigationItems.forEachIndexed { index, screen ->
                        BottomNavigationItem(
                            icon = {
                                if (index == 2) {
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(CircleShape)
                                            .background(Color.Green)
                                    ) {
                                        Icon(
                                            screen.icon(),
                                            contentDescription = screen.title,
                                            tint = Color.White,
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .size(40.dp)
                                        )
                                    }
                                } else {
                                    Icon(
                                        screen.icon(),
//                                    tint = if (index == 2) Color.Transparent else Color.Black,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                            selected = currentRoute == screen.route,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            },
                            selectedContentColor = Color.Red,
                            unselectedContentColor = Color.Gray
                        )
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
            composable(Screen.Catalog.route) { CatalogScreen(navController) }
            composable(Screen.Saved.route) { SavedScreen() }
            composable(Screen.Create.route) { NewProductScreen() }
            composable(Screen.Basket.route) { BasketScreen() }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable("loginScreen") { LogInScreen(navController) }
            composable("registrationScreen") { RegistrationScreen(navController) }
            composable("aboutScreen") { AboutScreen(navController) }
            composable("chatScreen") { ChatScreen(navController) }
            composable("settingsScreen") { SettingsScreen(navController) }
            composable("addressScreen") { AddressScreen(navController) }
            composable("ordersScreen") { MyOrdersScreen(navController) }
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

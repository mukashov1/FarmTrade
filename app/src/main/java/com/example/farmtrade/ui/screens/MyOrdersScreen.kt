package com.example.farmtrade.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MyOrdersScreen(navController: NavController) {
//    Column {
//        Row {
//            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "BackArrow")
//            Text(text = "My Orders")
//        }
//        Divider()
//        Card {
//            Row {
//                Text(text = "Order ID")
//                Text(text = "54545")
//            }
//        }
//    }
    ScaffoldWithBottomBarAndCutout()
}

@Composable
fun ScaffoldWithBottomBarAndCutout() {
    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val fabShape = RoundedCornerShape(50)


    // Scaffold is a pre-defined composable that implements the basic material design visual
    // layout structure. It takes in child composables for all the common elements that you see
    // in an app using material design - app bar, bottom app bar, floating action button, etc. It
    // also takes care of laying out these child composables in the correct positions - eg bottom
    // app bar is automatically placed at the bottom of the screen even though I didn't specify
    // that explicitly.
    Scaffold(
        topBar = { TopAppBar(title = { Text("Scaffold Examples") }) },
        bottomBar = {
            // We specify the shape of the FAB bu passing a shape composable (fabShape) as a
            // parameter to cutoutShape property of the BottomAppBar. It automatically creates a
            // cutout in the BottomAppBar based on the shape of the Floating Action Button.
            BottomAppBar(cutoutShape = fabShape) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                // We specify the same shape that we passed as the cutoutShape above.
                shape = fabShape,
                // We use the secondary color from the current theme. It uses the defaults when
                // you don't specify a theme (this example doesn't specify a theme either hence
                // it will just use defaults. Look at DarkModeActivity if you want to see an
                // example of using themes.
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->
            // We create a ScrollState that's "remember"ed  to add proper support for a scrollable component.
            // This allows us to also control the scroll position and other scroll related properties.


            // remember calculates the value passed to it only during the first composition. It then
            // returns the same value for every subsequent composition. More details are available in the
            // comments below.
            val scrollState = rememberScrollState()
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.
            Column(
                modifier = Modifier
                    .padding(padding)
                    .scrollable(scrollState, orientation = Orientation.Vertical)
            ) {
                repeat(100) {
                    // Card composable is a predefined composable that is meant to represent
                    // the card surface as specified by the Material Design specification. We
                    // also configure it to have rounded corners and apply a modifier.
                    Card(
                        backgroundColor = Color.Green,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun DefualPreviiew() {
ScaffoldWithBottomBarAndCutout()
}
package com.example.farmtrade.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.farmtrade.ui.screens.RegistrationScreen
import com.example.farmtrade.ui.theme.EffectiveMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                EffectiveMobileTheme {
                    val navController = rememberNavController()
                    RegistrationScreen(navController = navController)}
            }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EffectiveMobileTheme {
        // Preview your theme
    }
}
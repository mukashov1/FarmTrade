package com.example.farmtrade.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "Mukashov Bakdaulet",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
        Divider()
        ProfileItem(text = "My Orders", icon = Icons.Default.ShoppingCart)
        ProfileItem(text = "Delivery Address", icon = Icons.Default.LocationOn)
        ProfileItem(text = "Account Settings", icon = Icons.Default.Settings)
        ProfileItem(text = "Help", icon = Icons.Default.Warning)
        ProfileItem(text = "About", icon = Icons.Default.Info)
//        Spacer(modifier = Modifier.height(24.dp))
        LogoutButton(
            onLogOutClicked = {
                auth.signOut()
                navController.navigate("loginScreen")
            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun ProfileItem(text: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click here */ }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = text)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 18.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Arrow")
    }
    Divider()
}

@Composable
fun LogoutButton(
    onLogOutClicked: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onLogOutClicked,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.LightGray,
            contentColor = Color.Green,

            )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
            contentDescription = "LogOut",
            tint = Color.Green,
            modifier = Modifier.size(42.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = "Log Out", color = Color.Green, fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}
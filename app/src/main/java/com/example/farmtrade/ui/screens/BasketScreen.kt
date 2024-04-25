package com.example.farmtrade.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.farmtrade.data.db.BasketProduct
import com.example.farmtrade.ui.viewmodels.BasketScreenViewModel

@Composable
fun BasketScreen(navController: NavController) {
    val viewModel: BasketScreenViewModel = viewModel()
    val productsInBasket by viewModel.productsInBasket
    val subtotal by viewModel.subtotal
    val shipping = 5.00 // Assume a fixed shipping cost
    val total by viewModel.total


    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "KORZINA",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(productsInBasket) { product ->
                BasketItem(
                    product = product,
                    onQuantityChanged = { newQuantity ->
                        viewModel.updateQuantity(product.id!!, newQuantity)
                    },
                    onDeleteClicked = {
                        viewModel.deleteBasketFromFirestore(product.id!!)
                    }
                )
            }
        }


        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Divider(color = Color.LightGray, thickness = 1.dp)
            OrderSummary(subtotal, shipping, total)
            CheckoutButton { /* TODO: Implement the checkout logic here */ }
        }
    }
}

@Composable
fun BasketItem(
    product: BasketProduct,
    onQuantityChanged: (Int) -> Unit,
    onDeleteClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberImagePainter(
            data = product.imageUrl,
            builder = {
                crossfade(true)
            }
        )
        Image(
            painter = painter,
            contentDescription = "Product Image",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(60.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = product.title, fontSize = 16.sp)
            Text(text = "${product.price}", fontSize = 14.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { if (product.quantity > 1) onQuantityChanged(product.quantity - 1) }) {
                    Text("-")
                }
                Text("${product.quantity}", modifier = Modifier.padding(horizontal = 8.dp))
                Button(onClick = { onQuantityChanged(product.quantity + 1) }) {
                    Text("+")
                }
            }
        }
        Spacer(Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Text(
                text = "${Math.round(product.price * product.quantity * 100.00) / 100.00}",
                fontSize = 14.sp
            )
            Icon(
                modifier = Modifier.clickable { onDeleteClicked() },
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete",
            )
        }
    }
}


@Composable
fun OrderSummary(subtotal: Double, shipping: Double, total: Double) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Subtotal", modifier = Modifier.padding(vertical = 4.dp))
        Text("$${String.format("%.2f", subtotal)}", modifier = Modifier.padding(vertical = 4.dp))
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Shipping", modifier = Modifier.padding(vertical = 4.dp))
        Text("$${String.format("%.2f", shipping)}", modifier = Modifier.padding(vertical = 4.dp))
    }
    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 4.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Total", modifier = Modifier.padding(vertical = 4.dp))
        Text("$${String.format("%.2f", total)}", modifier = Modifier.padding(vertical = 4.dp))
    }
}


@Composable
fun CheckoutButton(onCheckout: () -> Unit) {
    Button(
        onClick = onCheckout,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Example button color
    ) {
        Text(
            text = "CHECKOUT",
            color = Color.White,
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp
        )
    }
}
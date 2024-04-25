package com.example.farmtrade.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.farmtrade.R
import com.example.farmtrade.data.db.ProductItem
import com.example.farmtrade.data.repository.DataStoreRepository
import com.example.farmtrade.ui.components.toggleBasket
import com.example.farmtrade.ui.viewmodels.SavedScreenViewModel

@Composable
fun SavedScreen(navController: NavController) {
    val repository = DataStoreRepository(LocalContext.current)
    val viewModel: SavedScreenViewModel = viewModel()
    val listOfProduct by viewModel.savedItems

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Saved Products",
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listOfProduct) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    shape = CardDefaults.elevatedShape,
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.light_grey)
                    ),
                    elevation = CardDefaults.outlinedCardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    SavedItem(
                        product = product,
                        onItemClicked = { navController.navigate("productScreen/${product.id}") },
                        addToBasketClicked = { toggleBasket(product) },
                        onCloseClicked = { viewModel.deleteFromFirestore(product.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }
            }
        }
    }

}

@Composable
fun SavedItem(
    product: ProductItem,
    onItemClicked: () -> Unit,
    addToBasketClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .clickable { onItemClicked() }
        ) {
            NetworkImage(url = product.images[0])
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable { onItemClicked() },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = product.title, fontSize = 18.sp, fontWeight = FontWeight(500))
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = Color.Yellow,
                    modifier = Modifier.size(15.dp),
                )
            }
            Text(
                text = "${product.price} ${product.priceUnit}",
                color = colorResource(id = R.color.grey),
                fontSize = 9.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${product.priceWithDiscount} ${product.priceUnit}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                )
                Text(
                    text = "${product.discount} %",
                    color = Color.White,
                    fontSize = 9.sp,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .background(
                            color = colorResource(id = R.color.pink),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(5.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onCloseClicked() }, content = {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "close",
                    tint = Color.Black,
                )
            })
            IconButton(onClick = { addToBasketClicked() }, content = {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart",
                )
            })
        }
    }
}
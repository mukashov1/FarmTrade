package com.example.farmtrade.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmtrade.R
import com.example.farmtrade.data.db.Product
import com.example.farmtrade.data.repository.DataStoreRepository
import com.example.farmtrade.ui.viewmodels.SavedScreenViewModel

@Composable
fun SavedScreen() {
    val repository = DataStoreRepository(LocalContext.current)
    val viewModel = SavedScreenViewModel(repository)
    val listOfProduct: List<Product> = viewModel.sampleSavedItems(repository)

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Saved Products")

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
                        onItemClicked = { /*TODO*/ },
                        addToBasketClicked = { /*TODO*/ },
                        onCloseClicked = { /*TODO*/ },
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
    product: Product,
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
        ) {
            Image(
                painter = painterResource(id = R.drawable.apple2),
                contentDescription = product.title,
                modifier = Modifier
//                .fillMaxHeight()
//                .size(48.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = product.title, fontSize = 18.sp, fontWeight = FontWeight(500))
            Row (horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = Color.Yellow,
                    modifier = Modifier.size(15.dp),
                )
                Text(text = "${product.feedback.rating}")
                Text(text = "(${product.feedback.count}) pikir")
            }
            Row {
                Text(text = "${product.price.priceWithDiscount} T")
                Column {
                    Text(text = "${product.price.price} T")
                    Text(text = "-${product.price.discount}%")
                }
            }
        }

        Column(modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { /*TODO*/ }, content = {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "close",
                    tint = Color.Black,
                )
            })
            IconButton(onClick = { /*TODO*/ }, content = {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "close",
                )
            })
        }
    }
}
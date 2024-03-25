package com.example.farmtrade.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.farmtrade.R
import com.example.farmtrade.data.repository.DataStoreRepository
import com.example.farmtrade.ui.viewmodels.ProductScreenViewModel
import com.example.farmtrade.ui.viewmodels.ProductScreenViewModelFactory
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ProductScreen(navController: NavController, productId: String) {
    val context = LocalContext.current
    val repository = remember { DataStoreRepository(context) }
    val productViewModel: ProductScreenViewModel = viewModel(
        factory = ProductScreenViewModelFactory(repository)
    )

    LaunchedEffect(productId) {
        productViewModel.loadProduct(productId)
    }

    val isLoading by productViewModel.isLoading.collectAsState()
    val product by productViewModel.product.collectAsState()

    if (isLoading) {
        CircularProgressIndicator() // Show a loading indicator while loading
    } else if (product != null) {
        val pagerState = rememberPagerState()
        val images = listOf(R.drawable.apple, R.drawable.item_1)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxSize(),
            ) {
                HorizontalPager(
                    count = images.size,
                    state = pagerState,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                ) { page ->
                    Image(
                        painter = painterResource(id = images[page]),
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = colorResource(id = R.color.pink),
                    inactiveColor = colorResource(id = R.color.light_grey),
                    indicatorHeight = 10.dp,
                    indicatorWidth = 10.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "${product!!.price.price} ${product!!.price.unit}",
                    color = colorResource(id = R.color.grey),
                    fontSize = 9.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = product!!.price.priceWithDiscount + " " + product!!.price.unit,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                    )
                    Text(
                        text = "${product!!.price.discount} %",
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
                Text(
                    text = product!!.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = product!!.subtitle,
                    fontSize = 10.sp,
//                        modifier = Modifier.height(40.dp)
                )

                Text(text = product!!.info[2].value)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.start_small),
                        contentDescription = "Star",
                        tint = colorResource(
                            id = R.color.orange
                        )
                    )
                    Text(
                        text = product!!.feedback.rating.toString(),
                        color = colorResource(id = R.color.orange)
                    )
                    Text(
                        text = "(${product!!.feedback.count})",
                        color = colorResource(id = R.color.grey)
                    )
                }

                AddToBasketButton(onAddToBasketClick = { println("BASKET") })

            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            ) {
                AddToFavoriteButton { println("FAVORITE") }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .size(16.dp),
                    painter = painterResource(id = androidx.appcompat.R.drawable.abc_ic_ab_back_material), // Replace with your correct icon resource
                    contentDescription = "Back to previews",
                    tint = colorResource(id = R.color.pink) // Set the tint color of the Icon
                )
            }
            // Add more UI elements to display product details as needed
        }
    } else {
        Text(text = "Product not found")
    }
}
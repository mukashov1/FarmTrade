package com.example.farmtrade.ui.screens

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    println("PRODUCT ID IN SCREEN $productId")
    LaunchedEffect(key1 = productId) {
        productViewModel.loadProduct(productId.toInt())
    }


    val isLoading by productViewModel.isLoading.collectAsState()
    val product by productViewModel.product.collectAsState()

    if (isLoading) {
        CircularProgressIndicator() // Show a loading indicator while loading
    } else {
        val pagerState = rememberPagerState()
        val images = product.images

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
                    NetworkImage(url = images[page])
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
                androidx.compose.material.Text(
                    text = product.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500)
                )
                androidx.compose.material.Text(
                    text = product.category,
                    fontSize = 10.sp,
                )
                androidx.compose.material.Text(
                    text = "${product.price} ${product.priceUnit}",
                    color = colorResource(id = R.color.grey),
                    fontSize = 9.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    androidx.compose.material.Text(
                        text = "${product.priceWithDiscount} ${product.priceUnit}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                    )
                    androidx.compose.material.Text(
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
    }
}
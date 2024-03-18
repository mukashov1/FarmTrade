package com.example.farmtrade.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmtrade.R
import com.example.farmtrade.data.db.Feedback
import com.example.farmtrade.data.db.InfoItem
import com.example.farmtrade.data.db.Price
import com.example.farmtrade.data.db.Product
import com.example.farmtrade.data.db.SortOption
import com.example.farmtrade.data.db.Tag
import com.example.farmtrade.ui.viewmodels.CatalogViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

    @Composable
    fun CatalogScreen(viewModel: CatalogViewModel = viewModel()) {
//        val catalogItems = viewModel.catalogItems.value
        val catalogItems = listOf(
            Product(
                id = "cbf0c984 - 7 c6c -4 ada -82 da -e29dc698bb50",
                title = "Jonywka",
                subtitle = "Qyzylordanyn jonywkasy",
                price = Price(price = "749", discount = 35, priceWithDiscount = "489", unit = "T"),
                feedback = Feedback(count = 51, rating = 4.5),
                tags = listOf("body"),
                available = 100,
                description = "Лосьон для тела `ESFOLIO` COENZYME Q10 Увлажняющий содержит минеральную воду и соду, способствует глубокому очищению пор от различных загрязнений, контроллирует работу сальных желез, сужает поры. Обладает мягким антиептическим действием, не пересушивает кожу. Минеральная вода тонизирует и смягчает кожу.",
                info = listOf(
                    InfoItem(title = "Артикул товара", value = "441187"),
                    InfoItem(title = "Область использования", value = "Kokonis"),
                    InfoItem(title = "Страна производства", value = "Qyzylorda")
                ),
                ingredients = "Glycerin Palmitic Acid, Stearic Acid, Capric Acid, Sodium Benzoate"
            ),
            Product(
                id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                title = "Alma",
                subtitle = "Almatynyn aport almasy",
                price = Price(
                    price = "749", discount = 35, priceWithDiscount = "489", unit = "T"
                ),
                feedback = Feedback(count = 51, rating = 4.5),
                tags = listOf("body"),
                available =
                100,
                description =
                "Лосьон для тела `ESFOLIO` COENZYME Q10 Увлажняющий содержит минеральную воду и соду, способствует глубокому очищению пор от различных загрязнений, контроллирует работу сальных желез, сужает поры . Обладает мягким антиептическим действием, не пересушивает кожу.Минеральная вода тонизирует и смягчает кожу .",
                info =
                listOf(
                    InfoItem(
                        title = "Артикул товара",
                        value = "441187"
                    ), InfoItem(
                        title = "Область использования",
                        value = "Fruit"
                    ), InfoItem(title = "Страна производства", value = "Almaty")
                ),
                ingredients =
                "Glycerin Palmitic Acid, Stearic Acid, Capric Acid, Sodium Benzoate"
            ),
            Product(
                id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                title = "Alma",
                subtitle = "Almatynyn aport almasy",
                price = Price(
                    price = "749", discount = 35, priceWithDiscount = "489", unit = "T"
                ),
                feedback = Feedback(count = 51, rating = 4.5),
                tags = listOf("body"),
                available =
                100,
                description =
                "Лосьон для тела `ESFOLIO` COENZYME Q10 Увлажняющий содержит минеральную воду и соду, способствует глубокому очищению пор от различных загрязнений, контроллирует работу сальных желез, сужает поры . Обладает мягким антиептическим действием, не пересушивает кожу.Минеральная вода тонизирует и смягчает кожу .",
                info =
                listOf(
                    InfoItem(
                        title = "Артикул товара",
                        value = "441187"
                    ), InfoItem(
                        title = "Область использования",
                        value = "Fruit"
                    ), InfoItem(title = "Страна производства", value = "Almaty")
                ),
                ingredients =
                "Glycerin Palmitic Acid, Stearic Acid, Capric Acid, Sodium Benzoate"
            ),Product(
                id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                title = "Alma",
                subtitle = "Almatynyn aport almasy",
                price = Price(
                    price = "749", discount = 35, priceWithDiscount = "489", unit = "T"
                ),
                feedback = Feedback(count = 51, rating = 4.5),
                tags = listOf("body"),
                available =
                100,
                description =
                "Лосьон для тела `ESFOLIO` COENZYME Q10 Увлажняющий содержит минеральную воду и соду, способствует глубокому очищению пор от различных загрязнений, контроллирует работу сальных желез, сужает поры . Обладает мягким антиептическим действием, не пересушивает кожу.Минеральная вода тонизирует и смягчает кожу .",
                info =
                listOf(
                    InfoItem(
                        title = "Артикул товара",
                        value = "441187"
                    ), InfoItem(
                        title = "Область использования",
                        value = "Fruit"
                    ), InfoItem(title = "Страна производства", value = "Almaty")
                ),
                ingredients =
                "Glycerin Palmitic Acid, Stearic Acid, Capric Acid, Sodium Benzoate"
            ),Product(
                id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                title = "Alma",
                subtitle = "Almatynyn aport almasy",
                price = Price(
                    price = "749", discount = 35, priceWithDiscount = "489", unit = "T"
                ),
                feedback = Feedback(count = 51, rating = 4.5),
                tags = listOf("body"),
                available =
                100,
                description =
                "Лосьон для тела `ESFOLIO` COENZYME Q10 Увлажняющий содержит минеральную воду и соду, способствует глубокому очищению пор от различных загрязнений, контроллирует работу сальных желез, сужает поры . Обладает мягким антиептическим действием, не пересушивает кожу.Минеральная вода тонизирует и смягчает кожу .",
                info =
                listOf(
                    InfoItem(
                        title = "Артикул товара",
                        value = "441187"
                    ), InfoItem(
                        title = "Область использования",
                        value = "Fruit"
                    ), InfoItem(title = "Страна производства", value = "Almaty")
                ),
                ingredients =
                "Glycerin Palmitic Acid, Stearic Acid, Capric Acid, Sodium Benzoate"
            )
        )
        var showSortMenu by remember { mutableStateOf(false) }
        var sortOption = viewModel.sortOption.value
        val selectedTag = viewModel.currentTag.value
    
    
        Column {
            Text(
                text = "FarmTrade",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SortButton(showSortMenu, sortOption) { selectedOption ->
                    showSortMenu = !showSortMenu
                    if (selectedOption != null) {
                        sortOption = selectedOption
                        viewModel.sortCatalogItems(sortOption)
                    }
                }
                FilterButton()
            }
    
            TagsCarousel(tags = Tag.entries, selectedTag = selectedTag, onTagSelected = {
                viewModel.filterCatalogItemsByTag(it)
                println(selectedTag)
            })
            ProductGridView(
                products = catalogItems,
                onProductClicked = { product ->
                    println("PRODUCT")
                }
            )
        }
    }
    
    @Composable
    fun SortButton(
        showSortMenu: Boolean,
        sortOption: SortOption,
        onSortSelected: (SortOption?) -> Unit
    ) {
        // Анимация поворота
        val rotationAngle by animateFloatAsState(targetValue = if (showSortMenu) 180f else 0f)
    
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onSortSelected(null) }
                    .wrapContentWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_by),
                    contentDescription = "Sort Icon"
                )
                Text(text = sortOption.title)
                // Применение анимации поворота к иконке
                Icon(
                    painter = painterResource(id = R.drawable.down_arrow),
                    contentDescription = "Sort",
                    modifier = Modifier.graphicsLayer(rotationZ = rotationAngle)
                )
            }
            DropdownMenu(
                expanded = showSortMenu,
                onDismissRequest = { onSortSelected(null) }
            ) {
                SortOption.entries.forEach { option ->
                    DropdownMenuItem(onClick = { onSortSelected(option) }) {
                        Text(text = option.title)
                    }
                }
            }
        }
    }
    
    @Composable
    fun FilterButton() {
        Row(horizontalArrangement = Arrangement.End) {
            Icon(painter = painterResource(id = R.drawable.filter), contentDescription = "Filter")
            Text(text = "Фильтры")
        }
    }
    
    @Composable
    fun TagItem(
        currentTag: Tag,
        isSelected: Boolean,
        onSelected: (Tag) -> Unit,
        onDeselected: () -> Unit
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(
                    if (isSelected) colorResource(id = R.color.dark_blue) else colorResource(
                        id = R.color.light_grey
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { onSelected(currentTag) }, // Clickable area for tag selection
        ) {
            Text(
                text = currentTag.title,
                color = if (isSelected) Color.White else colorResource(id = R.color.grey)
            )
            if (isSelected) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Small Close",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { onDeselected() } // Clickable area for tag deselection
                )
            }
        }
    }
    
    
    @Composable
    fun TagsCarousel(
        tags: List<Tag>,
        selectedTag: Tag,
        onTagSelected: (Tag) -> Unit
    ) {
        val scrollState = rememberScrollState()
    
        Row(
            modifier = Modifier
                .padding(PaddingValues(8.dp))
                .horizontalScroll(state = scrollState, enabled = true)
        ) {
            tags.forEach { tag ->
                TagItem(
                    currentTag = tag,
                    isSelected = selectedTag == tag,
                    onSelected = { onTagSelected(tag) },
                    onDeselected = { onTagSelected(Tag.All) } // Reset the filter when the close icon is clicked
                )
                // Добавляем небольшой отступ между тэгами
                Spacer(modifier = Modifier.width(8.dp)) // Adjust space as needed
            }
        }
    }
    
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ProductGridView(products: List<Product>, onProductClicked: (Product) -> Unit) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
        ) {
            items(products) { product ->
                ProductCard(product, onProductClicked)
            }
        }
    }
    
    @ExperimentalPagerApi
    @Composable
    fun ProductCard(product: Product, onProductClicked: (Product) -> Unit) {
        val images = mapProductToImages(product.id)
        val pagerState = rememberPagerState()
    
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onProductClicked(product) },
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, colorResource(id = R.color.light_grey))
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.padding(horizontal = 5.dp)
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
                            contentScale = ContentScale.Fit
                        )
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = colorResource(id = R.color.pink),
                        inactiveColor = colorResource(id = R.color.light_grey),
                        indicatorHeight = 3.dp,
                        indicatorWidth = 3.dp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    androidx.compose.material.Text(
                        text = "${product.price.price} ${product.price.unit}",
                        color = colorResource(id = R.color.grey),
                        fontSize = 9.sp,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material.Text(
                            text = product.price.priceWithDiscount + " " + product.price.unit,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                        )
                        androidx.compose.material.Text(
                            text = "${product.price.discount} %",
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
                    androidx.compose.material.Text(
                        text = product.title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500)
                    )
                    androidx.compose.material.Text(
                        text = product.subtitle,
                        fontSize = 10.sp,
//                        modifier = Modifier.height(40.dp)
                    )
                    
                    Text(text = product.info[2].value)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material.Icon(
                            painter = painterResource(id = R.drawable.start_small),
                            contentDescription = "Star",
                            tint = colorResource(
                                id = R.color.orange
                            )
                        )
                        androidx.compose.material.Text(
                            text = product.feedback.rating.toString(),
                            color = colorResource(id = R.color.orange)
                        )
                        androidx.compose.material.Text(
                            text = "(${product.feedback.count})",
                            color = colorResource(id = R.color.grey)
                        )
                    }
    
                    AddToBasketButton(onAddToBasketClick = { println("BASKET") })
    
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            ) {
                AddToFavoriteButton { println("FAVORITE") }
            }
    
        }
    }
    
    fun mapProductToImages(productId: String): List<Int> {
        // This function maps product IDs to drawable resources
        return when (productId) {
            "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(R.drawable.apple, R.drawable.apple)
            "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(R.drawable.item_1, R.drawable.item_2)
            "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(R.drawable.item_1, R.drawable.item_2)
            "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.item_1, R.drawable.item_2)
            "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.item_1, R.drawable.item_2)
            "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.item_1, R.drawable.item_2)
            "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.item_1, R.drawable.item_2)
            "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.item_1, R.drawable.item_2)
            else -> listOf(R.drawable.placeholder_image) // Placeholder if ID not found
        }
    }
    
    @Composable
    fun AddToFavoriteButton(onAddToFavoriteButton: () -> Unit) {
        Icon(
            modifier = Modifier.clickable { onAddToFavoriteButton() },
            painter = painterResource(id = R.drawable.heart_default), // Replace with your correct icon resource
            contentDescription = "Add to Favorite",
            tint = colorResource(id = R.color.pink) // Set the tint color of the Icon
        )
    }
    
    @Composable
    fun AddToBasketButton(onAddToBasketClick: () -> Unit) {
        val pinkColor = colorResource(id = R.color.pink)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clickable { onAddToBasketClick() }
                    .size(32.dp) // This is a typical size for an IconButton
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    val cornerRadius = CornerRadius(20f, 20f)
                    val path = Path().apply {
                        addRoundRect(
                            RoundRect(
                                rect = Rect(
                                    offset = Offset.Zero,
                                    size = Size(size.width, size.height),
                                ),
                                topLeft = cornerRadius,
                                bottomRight = cornerRadius
                            )
                        )
                    }
                    drawPath(
                        path,
                        color = pinkColor
                    )
                }
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add to Favorite",
                    tint = Color.White, // Icon tint color
                    modifier = Modifier
                        .size(24.dp) // Size of the Icon
                )
            }
        }
    }

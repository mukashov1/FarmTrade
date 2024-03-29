package com.example.farmtrade.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.Feedback
import com.example.farmtrade.data.db.InfoItem
import com.example.farmtrade.data.db.Price
import com.example.farmtrade.data.db.Product
import com.example.farmtrade.data.db.SortOption
import com.example.farmtrade.data.db.Tag
import com.example.farmtrade.data.repository.DataStoreRepository
import kotlinx.coroutines.launch

class CatalogViewModel(private val catalogDataStoreRepository: DataStoreRepository) : ViewModel() {
    private val _originalCatalogItems = mutableStateOf<List<Product>>(emptyList())
    private val _catalogItems = mutableStateOf<List<Product>>(emptyList())
    val catalogItems = _catalogItems
    val currentTag = mutableStateOf(Tag.All)
    var sortOption = mutableStateOf(SortOption.Popularity)
    val favoriteProducts = mutableStateOf(setOf<String>())

    fun sortCatalogItems(option: SortOption) {
        sortOption.value = option
        _catalogItems.value = when (option) {
            SortOption.Popularity -> _catalogItems.value.sortedByDescending { it.feedback.rating }
            SortOption.PriceDescending -> _catalogItems.value.sortedByDescending { it.price.priceWithDiscount.toDouble() }
            SortOption.PriceAscending -> _catalogItems.value.sortedBy { it.price.priceWithDiscount.toDouble() }
        }
    }

    fun filterCatalogItemsByTag(tag: Tag) {
        currentTag.value = tag
        val allItems = _originalCatalogItems.value
        _catalogItems.value = if (tag == Tag.All) {
            allItems
        } else {
            allItems.filter { it.tags.any { itemTag -> itemTag.equals(tag.title, ignoreCase = true) } }
        }
    }

    fun toggleFavorite(productId: String) {
        val currentFavorites = favoriteProducts.value.toMutableSet()
        if (currentFavorites.contains(productId)) {
            currentFavorites.remove(productId)
        } else {
            currentFavorites.add(productId)
        }
        favoriteProducts.value = currentFavorites
    }

    fun isProductFavorite(productId: String): Boolean {
        return favoriteProducts.value.contains(productId)
    }

    init {
        loadCatalogItems()
    }

    private fun loadCatalogItems() {
        val cataItems = listOf(
            Product(
                id = "cbf0c984 - 7 c6c -4 ada -82 da -e29dc698bb50",
                title = "Jonywka",
                subtitle = "Qyzylordanyn jonywkasy",
                price = Price(price = "800", discount = 35, priceWithDiscount = "800", unit = "T"),
                feedback = Feedback(count = 51, rating = 5.0),
                tags = listOf(Tag.Body.title),
                available = 100,
                description = "Лосьон для тела `ESFOLIO` COENZYME Q10 Увлажняющий содержит минеральную воду и соду, способствует глубокому очищению пор от различных загрязнений, контроллирует работу сальных желез, сужает поры. Обладает мягким антиептическим действием, не пересушивает кожу. Минеральная вода тонизирует и смягчает кожу.",
                info = listOf(
                    InfoItem(title = "Артикул товара", value = "441187"),
                    InfoItem(title = "Область использования", value = "kokonis"),
                    InfoItem(title = "Страна производства", value = "Qyzylorda")
                ),
                ingredients = "Glycerin Palmitic Acid, Stearic Acid, Capric Acid, Sodium Benzoate"
            ),
            Product(
                id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                title = "Alma",
                subtitle = "Almatynyn aport almasy",
                price = Price(
                    price = "600", discount = 20, priceWithDiscount = "450", unit = "T"
                ),
                feedback = Feedback(count = 100, rating = 4.8),
                tags = listOf("Kokonis"),
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
                    price = "400", discount = 35, priceWithDiscount = "250", unit = "T"
                ),
                feedback = Feedback(count = 200, rating = 4.5),
                tags = listOf("Jemis"),
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
                feedback = Feedback(count = 5, rating = 4.2),
                tags = listOf("Jem"),
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
                    price = "700", discount = 10, priceWithDiscount = "630", unit = "T"
                ),
                feedback = Feedback(count = 70, rating = 4.4),
                tags = listOf("Et"),
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
        viewModelScope.launch {
//            try {
//                val response = CatalogApiService.RetrofitInstance.api.getCatalogItems()
//                if (response.isSuccessful) {
                    _originalCatalogItems.value = cataItems
                    _catalogItems.value = cataItems
                    catalogDataStoreRepository.saveCatalogItems(cataItems)
//                } else {
//                    println("Error: ${response.errorBody()?.string()}")
//                }
//            } catch (e: Exception) {
//                println("Error loading catalog items: ${e.message}")
//                e.printStackTrace()
//            }
        }
    }
}

package com.example.farmtrade.data.db.newProduct

import android.net.Uri
import com.example.farmtrade.data.db.ProductItem

data class NewProductUploadUIState(
    val product: ProductItem = ProductItem(),
    val imageUris: List<Uri> = emptyList(),
    val isLoading: Boolean = false,
    val submitSuccess: Boolean? = null

)

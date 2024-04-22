package com.example.farmtrade.ui.viewmodels

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.db.ImageResponse
import com.example.farmtrade.data.db.ProductItem
import com.example.farmtrade.data.db.newProduct.NewProductUploadUIState
import com.example.farmtrade.data.repository.NewProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Locale

class NewProductViewModel(private val repository: NewProductRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(NewProductUploadUIState())
    val uiState = _uiState.asStateFlow()

    fun updateProduct(update: ProductItem.() -> ProductItem) {
        _uiState.value = _uiState.value.copy(product = _uiState.value.product.update())
    }

    fun sendImagesSequentially(context: Context, imageUris: List<Uri>) {
        viewModelScope.launch {
            var isSuccess = false // Flag to track if all images are uploaded successfully

            try {
                // Iterate over the images to send them
                for (uri in imageUris) {
                    val file = createFileFromUri(context, uri)
                    repository.sendImageForAutoFill(file) { success, imageResponse ->
                        if (success) {
                            updateProductDetails(imageResponse)
                            isSuccess = true // Set the flag to false if any image fails
                        } else {
                            Log.e(TAG, "Failed to autofill from image")
                        }
                    }
                    if (isSuccess) break // Exit the loop if any image fails
                }

                // Once all images are sent and processed, set isLoading to false
            } catch (e: Exception) {
                // Handle exceptions, if any
                Log.e(TAG, "Failed to send images", e)
            }
        }
    }

    private fun createFileFromUri(context: Context, uri: Uri): File {
        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r", null)
            ?: throw IllegalStateException("Could not open file descriptor.")
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(context.cacheDir, "tempImage")
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        return file
    }

    private fun updateProductDetails(imageResponse: ImageResponse) {
        _uiState.value = _uiState.value.copy(
            product = _uiState.value.product.copy(
                title = imageResponse.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                category = imageResponse.type.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            )
        )
    }

    fun setIsLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
        println("FUNCTION ${_uiState.value.isLoading}")
    }

    fun addImage(uri: Uri) {
        _uiState.value =
            _uiState.value.copy(imageUris = ArrayList(_uiState.value.imageUris).apply { add(uri) })
    }

    fun submitProduct() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val success =
                repository.uploadProductWithImages(_uiState.value.product, _uiState.value.imageUris)
            _uiState.value = _uiState.value.copy(isLoading = false, submitSuccess = success)
        }
    }
}
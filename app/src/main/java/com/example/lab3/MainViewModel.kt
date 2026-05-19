package com.example.lab3

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val storage = SelectionStorage(application)

    var productType by mutableStateOf<String?>(null)
        private set

    var brand by mutableStateOf<String?>(null)
        private set

    var saveStatus by mutableStateOf<String?>(null)
        private set

    fun selectProductType(type: String) { productType = type }

    fun selectBrand(newBrand: String) { brand = newBrand }

    fun isSelectionComplete() = productType != null && brand != null

    fun resetSelection() {
        productType = null
        brand = null
        saveStatus = null
    }

    fun saveToStorage(onSuccess: () -> Unit) {
        val type = productType ?: return
        val b = brand ?: return
        try {
            storage.save(type, b)
            saveStatus = "Збережено: $type від $b"
            onSuccess()
        } catch (e: Exception) {
            saveStatus = "Помилка запису"
        }
    }
}
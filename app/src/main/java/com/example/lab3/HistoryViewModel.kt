package com.example.lab3

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val storage = SelectionStorage(application)

    var selections by mutableStateOf<List<Selection>>(emptyList())
        private set

    init {
        loadAll()
    }

    fun loadAll() {
        selections = storage.getAll()
    }

    fun deleteById(id: Long) {
        storage.deleteById(id)
        loadAll()
    }
}
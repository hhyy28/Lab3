package com.example.lab3

import android.content.Context

data class Selection(
    val id: Long,
    val productType: String,
    val brand: String
)

class SelectionStorage(context: Context) {

    private val prefs = context.getSharedPreferences("selections_prefs", Context.MODE_PRIVATE)
    private val key = "selections_list"

    fun save(productType: String, brand: String) {
        val current = getAll().toMutableList()
        val newItem = Selection(
            id = System.currentTimeMillis(),
            productType = productType,
            brand = brand
        )
        current.add(0, newItem)
        val serialized = current.joinToString("|") { "${it.id};${it.productType};${it.brand}" }
        prefs.edit().putString(key, serialized).apply()
    }

    fun getAll(): List<Selection> {
        val raw = prefs.getString(key, "") ?: ""
        if (raw.isEmpty()) return emptyList()
        return raw.split("|").mapNotNull {
            val parts = it.split(";")
            if (parts.size == 3) {
                Selection(parts[0].toLong(), parts[1], parts[2])
            } else null
        }
    }

    fun deleteById(id: Long) {
        val current = getAll().filter { it.id != id }
        val serialized = current.joinToString("|") { "${it.id};${it.productType};${it.brand}" }
        prefs.edit().putString(key, serialized).apply()
    }
}
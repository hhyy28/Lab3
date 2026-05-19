package com.example.lab3

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun InputScreen(
    viewModel: MainViewModel,
    onNavigateToResult: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val context = LocalContext.current
    val productTypes = listOf("Смартфон", "Ноутбук")
    val brands = listOf("Apple", "Samsung")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Тип товару:", style = MaterialTheme.typography.titleMedium)

        productTypes.forEach { type ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = viewModel.productType == type,
                        onClick = { viewModel.selectProductType(type) }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = viewModel.productType == type,
                    onClick = { viewModel.selectProductType(type) }
                )
                Text(text = type, modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Фірма:", style = MaterialTheme.typography.titleMedium)

        brands.forEach { brand ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = viewModel.brand == brand,
                        onClick = { viewModel.selectBrand(brand) }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = viewModel.brand == brand,
                    onClick = { viewModel.selectBrand(brand) }
                )
                Text(text = brand, modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    if (viewModel.isSelectionComplete()) {
                        viewModel.saveToStorage {
                            Toast.makeText(context, viewModel.saveStatus, Toast.LENGTH_SHORT).show()
                            onNavigateToResult()
                        }
                    } else {
                        Toast.makeText(context, "Будь ласка, оберіть тип та фірму", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("ОК")
            }
            OutlinedButton(
                onClick = { viewModel.resetSelection() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToHistory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Відкрити")
        }
    }
}
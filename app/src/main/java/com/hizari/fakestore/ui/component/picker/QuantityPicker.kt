package com.hizari.fakestore.ui.component.picker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.theme.FakeStoreTheme

/**
 * Fake Store - com.hizari.fakestore.ui.component.picker
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
private fun PreviewQuantityPicker() {
    FakeStoreTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuantityPicker(
                onQuantityChange = {},
                quantity = 1,
            )
            QuantityPicker(
                onQuantityChange = {},
                quantity = 2,
            )
            QuantityPicker(
                onQuantityChange = {},
                quantity = 998,
            )
            QuantityPicker(
                onQuantityChange = {},
                quantity = 999,
            )
        }
    }
}

@Composable
fun QuantityPicker(
    onQuantityChange: (Int) -> Unit,
    quantity: Int,
) {
    val minQuantity = 1
    val maxQuantity = 999

    var quantityText by remember { mutableStateOf(quantity.toString()) }

    Surface(
        border = BorderStroke(
            color = MaterialTheme.colorScheme.primary,
            width = 1.dp,
        ),
        shape = RoundedCornerShape(percent = 50)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (quantity > minQuantity) {
                IconButton(onClick = {
                    val newQuantity = quantity - 1
                    quantityText = newQuantity.toString()
                    onQuantityChange.invoke(newQuantity)
                }) {
                    Icon(
                        contentDescription = stringResource(R.string.remove),
                        imageVector = Icons.Default.Remove,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Box(modifier = Modifier.size(48.dp))
            }

            BasicTextField(
                value = quantityText,
                onValueChange = { newText ->
                    val newQuantity = newText.toIntOrNull() ?: quantity
                    if (newQuantity in minQuantity..maxQuantity) {
                        quantityText = newText
                        onQuantityChange(newQuantity)
                    }
                },
                modifier = Modifier.width(48.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true
            )

            if (quantity < maxQuantity) {
                IconButton(onClick = {
                    val newQuantity = quantity + 1
                    quantityText = newQuantity.toString()
                    onQuantityChange.invoke(newQuantity)
                }) {
                    Icon(
                        contentDescription = stringResource(R.string.add),
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Box(modifier = Modifier.size(48.dp))
            }
        }
    }
}

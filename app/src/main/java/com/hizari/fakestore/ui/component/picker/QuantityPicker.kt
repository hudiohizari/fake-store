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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.ui.theme.Typography

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
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            QuantityPicker(
                onDecrement = {},
                onIncrement = {},
                quantity = 1,
            )
            QuantityPicker(
                onDecrement = {},
                onIncrement = {},
                quantity = 2,
            )
            QuantityPicker(
                onDecrement = {},
                onIncrement = {},
                quantity = 98,
            )
            QuantityPicker(
                onDecrement = {},
                onIncrement = {},
                quantity = 99,
            )
        }
    }
}

@Composable
fun QuantityPicker(quantity: Int, onIncrement: () -> Unit, onDecrement: () -> Unit) {
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

            if (quantity > 1) {
                IconButton(onClick = onDecrement) {
                    Icon(
                        contentDescription = stringResource(R.string.remove),
                        imageVector = Icons.Default.Remove,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Box(modifier = Modifier.size(48.dp))
            }

            Text(
                modifier = Modifier.width(28.dp),
                color = MaterialTheme.colorScheme.primary,
                text = quantity.toString(),
                textAlign = TextAlign.Center,
                style = Typography.titleMedium,
            )


            if (quantity < 99) {
                IconButton(onClick = onIncrement) {
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
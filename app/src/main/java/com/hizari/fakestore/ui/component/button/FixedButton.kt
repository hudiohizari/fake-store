package com.hizari.fakestore.ui.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.fakestore.ui.theme.FakeStoreTheme

/**
 * Fake Store - com.hizari.fakestore.ui.component.button
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
private fun PreviewFSFixedButton() {
    FakeStoreTheme {
        FixedButton(
            text = "Button"
        )
    }
}

@Composable
fun FixedButton(
    modifier: Modifier = Modifier,
    cardColor: CardColors = CardDefaults.cardColors().copy(
        containerColor = MaterialTheme.colorScheme.surface
    ),
    text: String,
    loading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = cardColor,
        shape = RectangleShape
    ) {
        FSButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = enabled,
            loading = loading,
            onClick = onClick,
            text = text
        )
    }
}
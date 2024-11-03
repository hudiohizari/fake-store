package com.hizari.fakestore.ui.component.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.fakestore.ui.component.item.ChipItem
import com.hizari.fakestore.ui.theme.FakeStoreTheme

/**
 * Fake Store - com.hizari.fakestore.ui.component.group
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
fun PreviewChipGroup() {
    FakeStoreTheme {
        ChipGroup(
            items = listOf("All", "Men's clothing", "Women's clothing", "Jewelery", "Electronics"),
            selected = "All",
            onSelectedChanged = {

            }
        )
    }
}

@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(all = 4.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(4.dp),
    items: List<String>,
    selected: String?,
    onSelectedChanged: (String) -> Unit,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(items) { item ->
            ChipItem(
                name = item,
                isSelected = selected == item,
                onSelectionChanged = {
                    onSelectedChanged(it)
                },
            )
        }
    }
}
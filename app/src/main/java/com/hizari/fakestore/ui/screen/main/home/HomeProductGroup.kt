package com.hizari.fakestore.ui.screen.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.domain.model.product.Product
import com.hizari.fakestore.ui.component.item.ProductGridItem
import com.hizari.fakestore.ui.component.state.DefaultEmpty
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.util.extention.bottomFadingEdge
import com.hizari.fakestore.util.extention.topFadingEdge
import kotlin.random.Random

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.home
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
fun PreviewHomeProductGroup() {
    var index = 0
    FakeStoreTheme {
        HomeProductGroup(
            onAddToCart = {},
            onClick = {},
            productList = List(10) {
                Product.mock(it.toLong())
            }
        )
    }
}

@Composable
fun HomeProductGroup(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(all = 16.dp),
    onAddToCart: (Product) -> Unit,
    onClick: (Product) -> Unit,
    productList: List<Product>,
) {
    val listState = rememberLazyStaggeredGridState()

    LazyVerticalStaggeredGrid(
        modifier = modifier
            .bottomFadingEdge(isVisible = listState.canScrollForward)
            .topFadingEdge(
                color = MaterialTheme.colorScheme.background,
                isVisible = listState.canScrollBackward
            ),
        contentPadding = contentPadding,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = listState,
        verticalItemSpacing = 8.dp,
    ) {
        items(productList) { product ->
            ProductGridItem(
                onAddToCart = { onAddToCart.invoke(product) },
                onClick = { onClick.invoke(product) },
                product = product
            )
        }

        if (productList.isEmpty()) {
            item(span = StaggeredGridItemSpan.FullLine) { DefaultEmpty() }
        }
    }
}

package com.hizari.fakestore.ui.component.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.domain.model.product.Product
import com.hizari.fakestore.ui.component.item.ProductGridItem
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
fun PreviewVerticalStaggeredProductGroup() {
    var index = 0
    FakeStoreTheme {
        VerticalStaggeredProductGroup(
            onAddToCart = {},
            onClick = {},
            productList = List(10) {
                Product(
                    id = it.toLong(),
                    title = "${index++} " + if (Random.nextBoolean()) "Fjallraven Laptops" else "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops, Fits 15 Laptops, Fits 15 Laptops, Fits 15 Laptops, Fits 15 Laptops",
                    price = "Rp${Random.nextInt(1000, 10000000)}",
                    description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                    category = "men's clothing",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                )
            }
        )
    }
}

@Composable
fun VerticalStaggeredProductGroup(
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
    }
}

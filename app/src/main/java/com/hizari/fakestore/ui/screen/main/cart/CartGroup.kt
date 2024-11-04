package com.hizari.fakestore.ui.screen.main.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.domain.model.cart.Cart
import com.hizari.domain.model.cart.CartProduct
import com.hizari.fakestore.ui.component.state.DefaultEmpty
import com.hizari.fakestore.ui.theme.FakeStoreTheme

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
fun PreviewCartGroup() {
    FakeStoreTheme {
        CartGroup(
            cart = Cart.mock(),
            onDelete = {},
            onQuantityChange = { _, _ -> }
        )
    }
}

@Composable
fun CartGroup(
    modifier: Modifier = Modifier,
    cart: Cart,
    contentPadding: PaddingValues = PaddingValues(all = 16.dp),
    onDelete: (CartProduct) -> Unit,
    onQuantityChange: (CartProduct, Int) -> Unit,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
    ) {
        items(cart.products) { cartProduct ->
            CartItem(
                cartProduct = cartProduct,
                onDelete = { onDelete.invoke(cartProduct) },
                onQuantityChange = { onQuantityChange.invoke(cartProduct, it) }
            )
        }
        if (cart.products.isEmpty()) {
            item { DefaultEmpty() }
        }
    }
}
package com.hizari.fakestore.ui.screen.main.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hizari.domain.model.cart.Cart
import com.hizari.fakestore.ui.component.state.DefaultEmpty

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.cart
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Composable
fun CartGroup(
    modifier: Modifier = Modifier,
    cartList: List<Cart>,
    contentPadding: PaddingValues = PaddingValues(all = 16.dp),
    onDelete: (Cart) -> Unit,
    onQuantityChange: (Cart, Int) -> Unit,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
    ) {
        items(cartList) { cart ->
            CartItem(
                cart = cart,
                onDelete = { onDelete.invoke(cart) },
                onQuantityChange = { onQuantityChange.invoke(cart, it) }
            )
        }
        if (cartList.isEmpty()) {
            item { DefaultEmpty() }
        }
    }
}
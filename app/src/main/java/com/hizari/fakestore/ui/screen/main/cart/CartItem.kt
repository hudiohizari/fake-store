package com.hizari.fakestore.ui.screen.main.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hizari.domain.model.cart.Cart
import com.hizari.domain.model.product.Product
import com.hizari.fakestore.ui.component.image.RemoteImage
import com.hizari.fakestore.ui.component.picker.QuantityPicker
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
fun PreviewCartItem() {
    FakeStoreTheme {
        CartItem(
            cart = Cart(
                id = 1,
                product = Product.mock(),
                quantity = 1
            ),
            onDelete = {},
            onQuantityChange = {}
        )
    }
}

@Composable
fun CartItem(
    cart: Cart,
    onDelete: () -> Unit,
    onQuantityChange: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RemoteImage(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentDescription = cart.product.title,
            imageUrl = cart.product.image,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = cart.product.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = cart.product.price,
                    fontSize = 14.sp,
                )

                QuantityPicker(
                    enableDelete = true,
                    onDelete = onDelete,
                    onQuantityChange = { onQuantityChange(it) },
                    quantity = cart.quantity,
                )
            }
        }
    }
}
package com.hizari.fakestore.ui.screen.main.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hizari.fakestore.navigation.main.MainNavAction
import com.hizari.fakestore.ui.component.button.FSButton
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.detail
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@Serializable
data class ProductDetailScreen(val productId: Long)

@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
) {
    ProductDetailScreenContent(
        modifier = modifier.fillMaxSize(),
        mainNavAction = mainNavAction
    )
}

@Preview
@Composable
fun PreviewProductDetailScreenContent() {
    FakeStoreTheme {
        ProductDetailScreenContent(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            mainNavAction = {}
        )
    }
}

@Composable
fun ProductDetailScreenContent(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Product Detail Screen")
        FSButton(
            onClick = {
                mainNavAction.invoke(MainNavAction.GoBack())
            },
            text = "Go to back to home"
        )
    }
}


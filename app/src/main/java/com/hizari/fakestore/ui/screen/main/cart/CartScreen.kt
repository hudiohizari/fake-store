package com.hizari.fakestore.ui.screen.main.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hizari.common.data.Result
import com.hizari.common.data.Result.Loading.dataOr
import com.hizari.common.extention.toast
import com.hizari.domain.model.cart.Cart
import com.hizari.fakestore.R
import com.hizari.fakestore.navigation.main.MainNavAction
import com.hizari.fakestore.ui.component.bar.FSTopAppBar
import com.hizari.fakestore.ui.component.button.FSButton
import com.hizari.fakestore.ui.component.state.DefaultResult
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.detail
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Serializable
data object CartScreen

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.doAction(CartViewAction.LoadCart)
    }

    CartScreenContent(
        modifier = modifier.fillMaxSize(),
        mainNavAction = mainNavAction,
        updateViewState = viewModel::updateViewState,
        viewState = viewState
    )
}

@Preview
@Composable
fun PreviewCartScreenContent() {
    FakeStoreTheme {
        CartScreenContent(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            mainNavAction = {},
            updateViewState = {},
            viewState = CartViewState()
        )
    }
}

@Composable
fun CartScreenContent(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
    updateViewState: ((CartViewState) -> CartViewState) -> Unit,
    viewState: CartViewState
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        FSTopAppBar(
            onBackPressed = { mainNavAction.invoke(MainNavAction.GoBack()) },
            title = stringResource(R.string.cart)
        )

        DefaultResult(
            result = viewState.cartResult,
            onSuccess = { data ->
                CartGroup(
                    modifier = Modifier.weight(1f),
                    cart = data,
                    onDelete = { cart ->
                        updateViewState { viewState ->
                            viewState.copy(
                                cartResult = Result.Success(
                                    data.copy(
                                        products = data.products.filter { it.product.id != cart.product.id }
                                    )
                                )
                            )
                        }
                    },
                    onQuantityChange = { cart, newQuantity ->
                        updateViewState { viewState ->
                            viewState.copy(
                                cartResult = Result.Success(
                                    data.copy(
                                        products = data.products.map {
                                            if (it.product.id == cart.product.id) {
                                                it.copy(quantity = newQuantity)
                                            } else {
                                                it
                                            }
                                        }
                                    )
                                )
                            )
                        }
                    }
                )

                Row(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.getTotalPrice(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    FSButton(
                        modifier = Modifier.padding(vertical = 8.dp),
                        enabled = viewState.cartResult.dataOr(Cart.empty()).products.isNotEmpty(),
                        onClick = { context.toast(R.string.checkout) },
                        text = stringResource(R.string.checkout)
                    )
                }
            }
        )
    }
}


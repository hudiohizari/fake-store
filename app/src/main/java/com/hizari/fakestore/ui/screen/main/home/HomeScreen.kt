package com.hizari.fakestore.ui.screen.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hizari.fakestore.R
import com.hizari.fakestore.navigation.main.MainNavAction
import com.hizari.fakestore.ui.component.bar.FSTopAppBar
import com.hizari.fakestore.ui.component.group.ChipGroup
import com.hizari.fakestore.ui.component.group.VerticalStaggeredProductGroup
import com.hizari.fakestore.ui.screen.main.detail.ProductDetailScreen
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.home
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@Serializable
data object HomeScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    HomeScreenContent(
        modifier = modifier.fillMaxSize(),
        mainNavAction = mainNavAction,
        updateViewState = viewModel::updateViewState,
        viewState = viewState
    )
}

@Preview
@Composable
fun PreviewHomeScreenContent() {
    FakeStoreTheme {
        HomeScreenContent(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            mainNavAction = {},
            updateViewState = {},
            viewState = HomeViewState()
        )
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
    updateViewState: ((HomeViewState) -> HomeViewState) -> Unit,
    viewState: HomeViewState
) {
    Column(modifier = modifier) {
        FSTopAppBar(
            actions = {
                IconButton(onClick = { }) {
                    BadgedBox(badge = {
                        if (viewState.cartCounts <= 0) return@BadgedBox
                        Badge {
                            Text(text = viewState.cartCounts.toString())
                        }
                    }) {
                        Icon(
                            contentDescription = stringResource(R.string.cart),
                            imageVector = Icons.Default.ShoppingCart,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            },
            title = stringResource(R.string.app_name)
        )
        ChipGroup(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                bottom = 0.dp,
                end = 16.dp,
                start = 16.dp,
                top = 16.dp,
            ),
            selected = viewState.selectedCategory,
            items = viewState.categoryList,
            onSelectedChanged = { item ->
                updateViewState {
                    it.copy(selectedCategory = item)
                }
            }
        )
        VerticalStaggeredProductGroup(
            modifier = Modifier.fillMaxWidth(),
            onAddToCart = { },
            onClick = {
                mainNavAction.invoke(MainNavAction.GoToScreen(ProductDetailScreen(productId = it.id)))
            },
            productList = viewState.productList,
        )
    }
}

package com.hizari.fakestore.ui.screen.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hizari.common.data.Result
import com.hizari.fakestore.R
import com.hizari.fakestore.navigation.main.MainNavAction
import com.hizari.fakestore.ui.component.bar.FSTopAppBar
import com.hizari.fakestore.ui.component.bar.SnackBar
import com.hizari.fakestore.ui.component.group.ChipGroup
import com.hizari.fakestore.ui.component.state.DefaultResult
import com.hizari.fakestore.ui.screen.main.cart.CartScreen
import com.hizari.fakestore.ui.screen.main.detail.ProductDetailScreen
import com.hizari.fakestore.ui.screen.main.profile.ProfileBottomSheetScreen
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

    LaunchedEffect(Unit) {
        viewModel.doAction(HomeViewAction.LoadCartCount)
        viewModel.doAction(HomeViewAction.LoadCategoryList)
    }

    LaunchedEffect(viewState.selectedCategory) {
        viewModel.doAction(HomeViewAction.LoadProductList(viewState.selectedCategory))
    }

    HomeScreenContent(
        modifier = modifier,
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
            modifier = Modifier,
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
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            FSTopAppBar(
                actions = {
                    IconButton(
                        onClick = {
                            mainNavAction.invoke(MainNavAction.GoToScreen(CartScreen))
                        }
                    ) {
                        BadgedBox(badge = {
                            if (viewState.cartCountResult !is Result.Success) return@BadgedBox
                            if (viewState.cartCountResult.data <= 0) return@BadgedBox
                            Badge {
                                Text(text = viewState.cartCountResult.data.toString())
                            }
                        }) {
                            Icon(
                                contentDescription = stringResource(R.string.cart),
                                imageVector = Icons.Default.ShoppingCart,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            updateViewState {
                                it.copy(
                                    showProfile = true
                                )
                            }
                        }
                    ) {
                        Icon(
                            contentDescription = stringResource(R.string.product_detail),
                            imageVector = Icons.Default.Person,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                title = stringResource(R.string.app_name)
            )

            DefaultResult(
                result = viewState.categoryListResult,
                onSuccess = { data ->
                    ChipGroup(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(
                            bottom = 0.dp,
                            end = 16.dp,
                            start = 16.dp,
                            top = 16.dp,
                        ),
                        selected = viewState.selectedCategory,
                        items = data,
                        onSelectedChanged = { item ->
                            updateViewState {
                                it.copy(selectedCategory = item)
                            }
                        }
                    )
                }
            )

            DefaultResult(
                result = viewState.productListResult,
                onSuccess = { data ->
                    HomeProductGroup(
                        modifier = Modifier.fillMaxWidth(),
                        onAddToCart = { product ->
                            updateViewState {
                                it.copy(
                                    showAddedToCart = true,
                                    productAddedToCart = product
                                )
                            }
                        },
                        onClick = {
                            mainNavAction.invoke(
                                MainNavAction.GoToScreen(
                                    ProductDetailScreen(
                                        productId = it.id
                                    )
                                )
                            )
                        },
                        productList = data,
                    )
                }
            )

            ProfileBottomSheetScreen(
                onDismissRequest = {
                    updateViewState {
                        it.copy(
                            showProfile = false
                        )
                    }
                },
                showBottomSheet = viewState.showProfile
            )
        }

        SnackBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            onClosed = {
                updateViewState {
                    it.copy(
                        showAddedToCart = false
                    )
                }
            },
            show = viewState.showAddedToCart,
            snackBar = SnackBar.success(
                text = stringResource(
                    R.string.successfully_added_x_to_cart,
                    viewState.productAddedToCart?.title.orEmpty()
                ),
            ),
        )
    }
}

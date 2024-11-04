package com.hizari.fakestore.ui.screen.main.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.hizari.fakestore.R
import com.hizari.fakestore.navigation.main.MainNavAction
import com.hizari.fakestore.ui.component.bar.FSTopAppBar
import com.hizari.fakestore.ui.component.bar.SnackBar
import com.hizari.fakestore.ui.component.button.FSButton
import com.hizari.fakestore.ui.component.image.RemoteImage
import com.hizari.fakestore.ui.component.picker.QuantityPicker
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.ui.theme.Typography
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
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    ProductDetailScreenContent(
        modifier = modifier.fillMaxSize(),
        mainNavAction = mainNavAction,
        updateViewState = viewModel::updateViewState,
        viewState = viewState
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
            mainNavAction = {},
            updateViewState = {},
            viewState = ProductDetailViewState()
        )
    }
}

@Composable
fun ProductDetailScreenContent(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
    updateViewState: ((ProductDetailViewState) -> ProductDetailViewState) -> Unit,
    viewState: ProductDetailViewState
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {

        val (ftab, cContent, sbAddToCart, cButtons) = createRefs()

        FSTopAppBar(
            modifier = Modifier
                .constrainAs(ftab) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                },
            onBackPressed = { mainNavAction.invoke(MainNavAction.GoBack()) },
            title = stringResource(R.string.product_detail)
        )

        Column(
            modifier = Modifier
                .constrainAs(cContent) {
                    top.linkTo(ftab.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(cButtons.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .verticalScroll(rememberScrollState()),
        ) {
            RemoteImage(
                modifier = Modifier
                    .aspectRatio(2f)
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(8.dp),
                contentDescription = viewState.product.title,
                imageUrl = viewState.product.image,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = viewState.product.price,
                style = Typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = viewState.product.title,
                style = Typography.titleLarge
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = viewState.product.rating,
                style = Typography.labelMedium
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = viewState.product.category,
                style = Typography.labelMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = viewState.product.description,
                style = Typography.bodyMedium
            )
        }

        SnackBar(
            modifier = Modifier
                .constrainAs(sbAddToCart) {
                    bottom.linkTo(cButtons.top, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    width = Dimension.fillToConstraints
                },
            onClosed = {
                updateViewState {
                    it.copy(
                        showAddToCart = false
                    )
                }
            },
            show = viewState.showAddToCart,
            snackBar = SnackBar.success(
                text = stringResource(R.string.successfully_added_n_items_to_cart, viewState.addedQuantity),
            ),
        )

        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .constrainAs(cButtons) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(16.dp),
            shape = RectangleShape,
            color = MaterialTheme.colorScheme.surface,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuantityPicker(
                    onQuantityChange = { quantity ->
                        updateViewState {
                            it.copy(quantity = quantity)
                        }
                    },
                    quantity = viewState.quantity,
                )
                FSButton(
                    enabled = viewState.showAddToCart.not(),
                    onClick = {
                        updateViewState {
                            it.copy(
                                addedQuantity = viewState.quantity,
                                quantity = 1,
                                showAddToCart = true,
                            )
                        }
                    },
                    text = stringResource(R.string.add_to_cart),
                )
            }
        }
    }
}


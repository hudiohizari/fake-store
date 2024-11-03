package com.hizari.fakestore.ui.screen.main.home

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
) {
    HomeScreenContent(
        modifier = modifier.fillMaxSize(),
        mainNavAction = mainNavAction
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
            mainNavAction = {}
        )
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Home Screen")
        FSButton(
            onClick = {
                mainNavAction.invoke(MainNavAction.GoToScreen(ProductDetailScreen("id")))
            },
            text = "Go to detail"
        )
    }
}

package com.hizari.fakestore.ui.screen.main.cart

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
) {
    CartScreenContent(
        modifier = modifier.fillMaxSize(),
        mainNavAction = mainNavAction
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
            mainNavAction = {}
        )
    }
}

@Composable
fun CartScreenContent(
    modifier: Modifier = Modifier,
    mainNavAction: (action: MainNavAction) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Cart Screen")
        FSButton(
            onClick = {
                mainNavAction.invoke(MainNavAction.GoBack())
            },
            text = "Go back"
        )
    }
}


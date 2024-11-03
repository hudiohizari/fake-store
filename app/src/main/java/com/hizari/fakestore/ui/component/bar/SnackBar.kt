package com.hizari.fakestore.ui.component.bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.ui.theme.LocalExtendedColors
import com.hizari.fakestore.ui.theme.Typography
import kotlinx.coroutines.delay

/**
 * Fake Store - com.hizari.fakestore.ui.component.bar
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

data class SnackBar(
    val text: String,
    val type: Type
) {

    companion object {
        fun error(text: String) = SnackBar(text, Type.Error)
        fun normal(text: String) = SnackBar(text, Type.Normal)
        fun success(text: String) = SnackBar(text, Type.Success)
        fun warning(text: String) = SnackBar(text, Type.Warning)
    }

    enum class Type {
        Error, Normal, Success, Warning
    }

}

@Composable
fun SnackBar(
    modifier: Modifier = Modifier,
    onClosed: () -> Unit = {},
    snackBar: SnackBar,
    show: Boolean = false,
    displayDuration: Long = 2000L,
) {
    var visible by remember { mutableStateOf(show) }

    LaunchedEffect(show) {
        if (show) {
            visible = true
            delay(displayDuration)
            visible = false
            onClosed.invoke()
        }
    }

    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 300),
            initialOffsetY = { it / 2 },
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 300),
            targetOffsetY = { it * 2 },
        ),
        visible = visible
    ) {
        Content(
            modifier = modifier,
            snackBar = snackBar
        )
    }
}

@Preview
@Composable
private fun PreviewContent() {
    FakeStoreTheme {
        Column {
            Content(
                snackBar = SnackBar.error("Error")
            )
            Spacer(Modifier.height(4.dp))
            Content(
                snackBar = SnackBar.normal("Normal")
            )
            Spacer(Modifier.height(4.dp))
            Content(
                snackBar = SnackBar.success("Success")
            )
            Spacer(Modifier.height(4.dp))
            Content(
                snackBar = SnackBar.warning("Warning")
            )
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    snackBar: SnackBar
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(
            color = when (snackBar.type) {
                SnackBar.Type.Error -> MaterialTheme.colorScheme.error
                SnackBar.Type.Normal -> MaterialTheme.colorScheme.surface
                SnackBar.Type.Success -> LocalExtendedColors.current.success
                SnackBar.Type.Warning -> LocalExtendedColors.current.warning
            },
            width = 1.dp,
        ),
        colors = CardDefaults.cardColors().copy(
            containerColor = when (snackBar.type) {
                SnackBar.Type.Error -> MaterialTheme.colorScheme.errorContainer
                SnackBar.Type.Normal -> MaterialTheme.colorScheme.background
                SnackBar.Type.Success -> LocalExtendedColors.current.successContainer
                SnackBar.Type.Warning -> LocalExtendedColors.current.warningContainer
            }
        ),
        shape = RoundedCornerShape(4.dp),
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = when (snackBar.type) {
                SnackBar.Type.Error -> MaterialTheme.colorScheme.error
                SnackBar.Type.Normal -> MaterialTheme.colorScheme.onBackground
                SnackBar.Type.Success -> LocalExtendedColors.current.success
                SnackBar.Type.Warning -> LocalExtendedColors.current.warning
            },
            style = Typography.bodyMedium,
            text = snackBar.text,
        )

    }
}
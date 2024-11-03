package com.hizari.fakestore.ui.component.bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.ui.theme.Typography

/**
 * Fake Store - com.hizari.fakestore.ui.component.bar
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
private fun PreviewFSTopAppBar() {
    FakeStoreTheme {
        Column {
            FSTopAppBar(title = stringResource(R.string.app_name))
            Spacer(Modifier.height(8.dp))
            FSTopAppBar(
                onBackPressed = {},
                title = "Profile"
            )
            Spacer(Modifier.height(8.dp))
            FSTopAppBar(
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            contentDescription = "Close",
                            imageVector = Icons.Default.Close,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                title = "Profile"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FSTopAppBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    onBackPressed: (() -> Unit)? = null,
    title: String,
) {
    TopAppBar(
        modifier = modifier,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.titleLarge.copy(
                    fontSize = 18.sp
                ),
                text = title,
            )
        },
        navigationIcon = {
            if (onBackPressed == null) return@TopAppBar
            IconButton(onClick = onBackPressed) {
                Icon(
                    contentDescription = stringResource(R.string.go_up),
                    imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        )
    )
}
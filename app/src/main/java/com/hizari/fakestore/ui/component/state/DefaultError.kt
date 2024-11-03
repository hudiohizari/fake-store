package com.hizari.fakestore.ui.component.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.component.button.FSButton
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.ui.theme.Typography

/**
 * Fake Store - com.hizari.fakestore.ui.component.state
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
private fun PreviewDefaultError() {
    FakeStoreTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DefaultError(
                onRetry = { }
            )
            DefaultError(
                message = "Some Error",
                onRetry = {}
            )
            DefaultError(
                message = "Error....",
                onRetry = { }
            )
            DefaultError(
                message = "Error....",
            )
        }
    }
}

@Composable
fun DefaultError(
    modifier: Modifier = Modifier,
    message: String? = null,
    messageColor: Color = MaterialTheme.colorScheme.tertiary,
    onRetry: (() -> Unit)? = null
) {

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (kbsError, tError) = createRefs()

        onRetry?.let {
            FSButton(
                modifier = Modifier
                    .constrainAs(kbsError) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, 24.dp)
                    },
                text = stringResource(id = R.string.retry),
                onClick = it
            )
        } ?: run {
            Box(
                modifier = Modifier
                    .constrainAs(kbsError) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, 8.dp)
                    }
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(tError) {
                    bottom.linkTo(parent.bottom, 24.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(kbsError.bottom, 16.dp)
                },
            color = messageColor,
            fontSize = 14.sp,
            style = Typography.labelMedium,
            text = message ?: stringResource(id = R.string.sorry_something_went_wrong),
        )
    }
}
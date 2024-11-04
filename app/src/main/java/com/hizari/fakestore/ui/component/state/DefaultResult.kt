package com.hizari.fakestore.ui.component.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hizari.common.data.Result

/**
 * Fake Store - com.hizari.fakestore.ui.component.state
 *
 * Created by hudiohizari on 04/11/24.
 * https://github.com/hudiohizari
 *
 */

@Composable
fun <T> DefaultResult(
    modifier: Modifier = Modifier,
    result: Result<T>,
    onError: @Composable (String) -> Unit = { message ->
        DefaultError(
            modifier = Modifier.fillMaxWidth(),
            message = message
        )
    },
    onLoading: @Composable () -> Unit = {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp),
            color = MaterialTheme.colorScheme.primary,
        )
    },
    onSuccess: @Composable (T) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (result) {
            is Result.Error -> onError(result.asMessage())
            is Result.Loading -> onLoading()
            is Result.Success -> onSuccess(result.data)
            else -> Box { }
        }
    }
}

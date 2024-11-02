package com.hizari.fakestore.ui.component.field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.ui.theme.defaultOutlinedTextFieldColors

/**
 * Fake Store - com.hizari.fakestore.ui.component.field
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
private fun PreviewPasswordTextField() {
    FakeStoreTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            PasswordTextField(
                enabled = false,
                label = stringResource(R.string.password),
                onValueChange = {},
                value = ""
            )
            PasswordTextField(
                label = stringResource(R.string.password),
                onValueChange = {},
                value = ""
            )
        }
    }
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    errorText: String = "",
    error: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: String,
    onValueChange: (String) -> Unit,
    value: String,
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        colors = defaultOutlinedTextFieldColors(),
        enabled = enabled,
        isError = error,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Password
        ),
        label = { Text(label) },
        onValueChange = onValueChange,
        singleLine = true,
        supportingText = if (!error) null else {
            { Text(text = errorText) }
        },
        trailingIcon = {
            IconButton(
                enabled = enabled,
                onClick = { showPassword = !showPassword }
            ) {
                Icon(
                    imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = if (showPassword) {
                        stringResource(R.string.hide_password)
                    } else {
                        stringResource(R.string.show_password)
                    }
                )
            }
        },
        value = value,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}

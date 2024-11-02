package com.hizari.fakestore.ui.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.hizari.common.extention.isNotNullAndEmpty
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.component.button.FixedButton
import com.hizari.fakestore.ui.component.field.PasswordTextField
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import com.hizari.fakestore.ui.theme.defaultOutlinedTextFieldColors
import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.fakestore.ui.screen.auth.login
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@Serializable
data object LoginScreen

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    LoginScreenContent(
        modifier = modifier,
        doAction = viewModel::doAction,
        viewState = viewState,
        updateViewState = viewModel::updateViewState
    )
}

@Preview
@Composable
private fun PreviewLoginScreenContent() {
    FakeStoreTheme {
        LoginScreenContent(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            doAction = {},
            updateViewState = {},
            viewState = LoginViewState()
        )
    }
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    doAction: (action: LoginViewAction) -> Unit,
    updateViewState: ((LoginViewState) -> LoginViewState) -> Unit,
    viewState: LoginViewState,
) {

    val (username, password, passwordError) = viewState

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (cScroll, bLogin) = createRefs()

        Column(
            modifier = modifier
                .constrainAs(cScroll) {
                    bottom.linkTo(bLogin.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            ConstraintLayout(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                val (iIcon, tUsername, tPassword) = createRefs()

                Image(
                    modifier = Modifier
                        .constrainAs(iIcon) {
                            end.linkTo(parent.end, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            top.linkTo(parent.top, 32.dp)
                            height = Dimension.wrapContent
                            width = Dimension.preferredValue(128.dp)
                        },
                    contentDescription = stringResource(R.string.app_name),
                    painter = painterResource(R.drawable.img_app_logo),
                )

                OutlinedTextField(
                    modifier = Modifier
                        .constrainAs(tUsername) {
                            end.linkTo(parent.end, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            top.linkTo(iIcon.bottom, 16.dp)
                            width = Dimension.fillToConstraints
                        },
                    colors = defaultOutlinedTextFieldColors(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    label = {
                        Text(text = stringResource(R.string.username))
                    },
                    singleLine = true,
                    onValueChange = { value ->
                        updateViewState {
                            it.copy(
                                username = value,
                                passwordError = ""
                            )
                        }
                    },
                    value = username,
                )

                PasswordTextField(
                    modifier = Modifier
                        .constrainAs(tPassword) {
                            end.linkTo(parent.end, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            top.linkTo(tUsername.bottom, 16.dp)
                            width = Dimension.fillToConstraints
                        },
                    error = passwordError.isNotNullAndEmpty(),
                    errorText = passwordError,
                    keyboardActions = KeyboardActions(
                        onSend = {
                            doAction(
                                LoginViewAction.DoLogin {

                                }
                            )
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    label = stringResource(R.string.password),
                    onValueChange = { value ->
                        updateViewState {
                            it.copy(
                                password = value,
                                passwordError = ""
                            )
                        }
                    },
                    value = password
                )
            }
        }

        val buttonEnabled = username.isNotNullAndEmpty() && password.isNotNullAndEmpty()

        FixedButton(
            modifier = Modifier
                .constrainAs(bLogin) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                },
            enabled = buttonEnabled,
            onClick = {
                doAction(
                    LoginViewAction.DoLogin {

                    }
                )
            },
            text = stringResource(R.string.login)
        )
    }
}
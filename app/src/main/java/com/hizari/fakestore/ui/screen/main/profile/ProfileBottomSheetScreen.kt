package com.hizari.fakestore.ui.screen.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hizari.common.extention.toast
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.component.button.FSButton
import com.hizari.fakestore.ui.theme.FakeStoreTheme

/**
 * Fake Store - com.hizari.fakestore.ui.screen.main.profile
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheetScreen(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    showBottomSheet: Boolean,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    if (showBottomSheet.not()) return

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    val viewState by viewModel.viewState.collectAsState()

    ModalBottomSheet(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        ProfileBottomSheetContent(
            viewState = viewState
        )
    }

}

@Preview
@Composable
private fun PreviewProfileBottomSheetContent() {
    FakeStoreTheme {
        ProfileBottomSheetContent(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            viewState = ProfileViewState()
        )
    }
}

@Composable
fun ProfileBottomSheetContent(
    modifier: Modifier = Modifier,
    viewState: ProfileViewState
) {
    val context = LocalContext.current
    val user = viewState.user

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.user_info),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.email_label, user.email)
        )
        Text(
            text = stringResource(id = R.string.phone_label, user.phone)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.name_label),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.first_name_label, user.name.firstname)
        )
        Text(
            text = stringResource(id = R.string.last_name_label, user.name.lastname)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.address_label),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.city_label, user.address.city)
        )
        Text(
            text = stringResource(id = R.string.street_label, user.address.street, user.address.number)
        )
        Text(
            text = stringResource(id = R.string.zipcode_label, user.address.zipcode)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.geolocation_label),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.latitude_label, user.address.geoLocation.lat)
        )
        Text(
            text = stringResource(id = R.string.logout)
        )

        Spacer(modifier = Modifier.height(16.dp))
        FSButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.toast(R.string.logout) },
            text = stringResource(id = R.string.logout)
        )
    }
}
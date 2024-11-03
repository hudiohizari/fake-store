package com.hizari.fakestore.ui.component.image

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.theme.FakeStoreTheme

/**
 * Fake Store - com.hizari.fakestore.ui.component.image
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Preview
@Composable
fun PreviewRemoteImage() {
    FakeStoreTheme {
        RemoteImage(
            modifier = Modifier.size(48.dp),
            contentDescription = "",
            imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        )
    }
}

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String,
    imageUrl: String,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
    ) {
        when (painter.state) {
            is State.Loading -> {
                SubcomposeAsyncImageContent(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(R.drawable.ic_image_placeholder)
                )
            }
            is State.Error -> {
                SubcomposeAsyncImageContent(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(R.drawable.ic_broken_image)
                )
            }
            else -> {
                SubcomposeAsyncImageContent(
                    contentScale = contentScale
                )
            }
        }
    }
}
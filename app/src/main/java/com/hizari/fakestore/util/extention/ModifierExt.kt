package com.hizari.fakestore.util.extention

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Fake Store - com.hizari.fakestore.util.extention
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Composable
private fun defaultColor() = MaterialTheme.colorScheme.surface

@Composable
private fun defaultWidth() = 8.dp

@Composable
private fun defaultAnimationSpec(): AnimationSpec<Dp> = tween(250)

private enum class FadingSide {
    LEFT, RIGHT, BOTTOM, TOP
}

@Composable
private fun Modifier.fadingEdge(
    vararg sides: FadingSide,
    isVisible: Boolean,
    color: Color = defaultColor(),
    width: Dp = defaultWidth(),
    animationSpec: AnimationSpec<Dp> = defaultAnimationSpec()
) = composed {

    val animatedWidth = animateDpAsState(
        targetValue = if (isVisible) width else 0.dp,
        animationSpec = animationSpec,
        label = "Fade width"
    ).value

    drawWithContent {
        fun Size.getFadeOffsets(side: FadingSide): Pair<Offset, Offset> {
            return when (side) {
                FadingSide.LEFT -> Offset.Zero to Offset(width.toPx(), 0f)
                FadingSide.RIGHT -> Offset(width.toPx(), 0f) to Offset.Zero
                FadingSide.BOTTOM -> Offset(0f, height) to Offset.Zero
                FadingSide.TOP -> Offset.Zero to Offset(0f, height)
            }
        }

        drawContent()

        sides.forEach { side ->
            val (start, end) = this.size.getFadeOffsets(side)

            val widthPx = animatedWidth.toPx()

            val fraction = when (side) {
                FadingSide.LEFT, FadingSide.RIGHT -> widthPx / this.size.width
                FadingSide.BOTTOM, FadingSide.TOP -> widthPx / this.size.height
            }

            drawRect(
                brush = Brush.linearGradient(
                    0f to color,
                    fraction to Color.Transparent,
                    start = start,
                    end = end
                ),
                size = this.size
            )
        }
    }
}

@Composable
fun Modifier.leftFadingEdge(
    isVisible: Boolean,
    color: Color = defaultColor(),
    width: Dp = defaultWidth(),
    animationSpec: AnimationSpec<Dp> = defaultAnimationSpec()
) = fadingEdge(
    FadingSide.LEFT,
    color = color,
    width = width,
    isVisible = isVisible,
    animationSpec = animationSpec
)

@Composable
fun Modifier.rightFadingEdge(
    isVisible: Boolean,
    color: Color = defaultColor(),
    width: Dp = defaultWidth(),
    animationSpec: AnimationSpec<Dp> = defaultAnimationSpec()
) = fadingEdge(
    FadingSide.RIGHT,
    color = color,
    width = width,
    isVisible = isVisible,
    animationSpec = animationSpec
)

@Composable
fun Modifier.bottomFadingEdge(
    isVisible: Boolean,
    color: Color = defaultColor(),
    width: Dp = defaultWidth(),
    animationSpec: AnimationSpec<Dp> = defaultAnimationSpec()
) = fadingEdge(
    FadingSide.BOTTOM,
    color = color,
    width = width,
    isVisible = isVisible,
    animationSpec = animationSpec
)

@Composable
fun Modifier.topFadingEdge(
    isVisible: Boolean,
    color: Color = defaultColor(),
    width: Dp = defaultWidth(),
    animationSpec: AnimationSpec<Dp> = defaultAnimationSpec()
) = fadingEdge(
    FadingSide.TOP,
    color = color,
    width = width,
    isVisible = isVisible,
    animationSpec = animationSpec
)
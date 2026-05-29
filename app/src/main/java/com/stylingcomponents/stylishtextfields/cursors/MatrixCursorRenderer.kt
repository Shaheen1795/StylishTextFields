package com.stylingcomponents.stylishtextfields.cursors
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.xr.compose.testing.toDp
import com.stylingcomponents.stylishtextfields.interfaces.CursorRenderer
class MatrixCursorRenderer : CursorRenderer {

    @Composable
    override fun brush(
        isFocused: Boolean
    ): Brush {

        val infinite =
            rememberInfiniteTransition(
                label = ""
            )

        val alpha by infinite.animateFloat(

            initialValue = 0.2f,

            targetValue = 1f,

            animationSpec = infiniteRepeatable(

                animation = tween(300),

                repeatMode = RepeatMode.Reverse
            ),

            label = ""
        )

        return Brush.verticalGradient(

            listOf(

                Color.Green.copy(alpha),

                Color(0xFF00FF99).copy(alpha)
            )
        )
    }
}
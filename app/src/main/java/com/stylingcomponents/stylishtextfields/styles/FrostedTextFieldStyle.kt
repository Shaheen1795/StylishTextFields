package com.stylingcomponents.stylishtextfields.styles
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldStyle
class FrostedTextFieldStyle : TextFieldStyle {

    @Composable
    override fun containerModifier(
        isFocused: Boolean
    ): Modifier {

        return Modifier
            .graphicsLayer {
                alpha = 0.95f
            }
            .background(
                Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(20.dp)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.2f),
                RoundedCornerShape(20.dp)
            )
    }

    @Composable
    override fun colors(): TextFieldColors {

        return TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    }
}
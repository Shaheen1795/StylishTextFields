package com.stylingcomponents.stylishtextfields.styles

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldStyle

class AuroraTextFieldStyle : TextFieldStyle {

    @Composable
    override fun containerModifier(
        isFocused: Boolean
    ): Modifier {

        val infinite = rememberInfiniteTransition(label = "")

        val offset by infinite.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    5000,
                    easing = LinearEasing
                )
            ),
            label = ""
        )

        return Modifier
            .drawBehind {

                drawRoundRect(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF00F5A0),
                            Color(0xFF00D9F5),
                            Color(0xFFB621FE)
                        ),
                        start = Offset(offset, 0f),
                        end = Offset(offset + size.width, size.height)
                    ),
                    cornerRadius = CornerRadius(40f),
                    style = Stroke(width = 6f)
                )
            }
    }

    @Composable
    override fun colors(): TextFieldColors {

        return TextFieldDefaults.colors(
            focusedContainerColor = Color.Black,
            unfocusedContainerColor = Color(0xFF111111),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White
        )
    }
}
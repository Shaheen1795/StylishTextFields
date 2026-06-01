package com.stylingcomponents.stylishtextfields.effects

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.BorderEffect

abstract class BaseBorderEffect(
    protected val borderColor: Color
) : BorderEffect {

    protected fun DrawScope.drawMainBorder() {

        drawRoundRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    borderColor,
                    Color.White,
                    borderColor
                )
            ),

            size = size,

            cornerRadius = CornerRadius(
                24.dp.toPx()
            ),

            style = Stroke(
                width = 3.dp.toPx()
            )
        )
    }
}
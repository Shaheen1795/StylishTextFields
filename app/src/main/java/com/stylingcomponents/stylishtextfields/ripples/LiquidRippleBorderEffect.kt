package com.stylingcomponents.stylishtextfields.ripples

import com.stylingcomponents.stylishtextfields.effects.BaseBorderEffect
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.models.EffectParticle
import kotlin.math.sin

class LiquidRippleBorderEffect :
    BaseBorderEffect(Color.Blue) {

    override fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<EffectParticle>
    ) {

        with(scope) {

            val distortion =
                sin(progress * 20f) * 12f

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Blue,
                        Color.Cyan
                    )
                ),

                topLeft = Offset(
                    distortion,
                    distortion
                ),

                size = Size(
                    size.width,
                    size.height
                ),

                cornerRadius = CornerRadius(
                    28.dp.toPx()
                ),

                style = Stroke(
                    width = 5.dp.toPx()
                )
            )

            drawMainBorder()
        }
    }
}
package com.stylingcomponents.stylishtextfields.ripples

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.effects.BaseBorderEffect
import com.stylingcomponents.stylishtextfields.models.EffectParticle

class NeonPlasmaBorderEffect :
    BaseBorderEffect(Color.Magenta) {

    override fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<EffectParticle>
    ) {

        with(scope) {

            val alpha =
                (1f - progress)
                    .coerceAtLeast(0f)

            drawRoundRect(
                Brush.sweepGradient(
                    colors = listOf(
                        Color.Red.copy(alpha = alpha),
                        Color.Magenta.copy(alpha),
                        Color.Cyan.copy(alpha),
                        Color.Green.copy(alpha),
                        Color.Yellow.copy(alpha),
                        Color.Red.copy(alpha)
                    )
                ),

                size = size,

                cornerRadius = CornerRadius(
                    28.dp.toPx()
                ),

                style = Stroke(
                    width = 6.dp.toPx()
                )
            )

            drawMainBorder()
        }
    }
}
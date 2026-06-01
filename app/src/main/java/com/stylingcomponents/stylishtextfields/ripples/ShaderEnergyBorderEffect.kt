package com.stylingcomponents.stylishtextfields.ripples

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.effects.BaseBorderEffect
import com.stylingcomponents.stylishtextfields.models.EffectParticle

class ShaderEnergyBorderEffect :
    BaseBorderEffect(Color.Cyan) {

    override fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<EffectParticle>
    ) {

        with(scope) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Cyan.copy(alpha = 0.8f),
                        Color.Magenta.copy(alpha = 0.8f),
                        Color.Transparent
                    ),

                    start = Offset(
                        size.width * progress - 300f,
                        0f
                    ),

                    end = Offset(
                        size.width * progress,
                        size.height
                    )
                ),

                size = size,

                cornerRadius = CornerRadius(
                    28.dp.toPx()
                ),

                blendMode = BlendMode.Screen
            )

            drawMainBorder()
        }
    }
}
package com.stylingcomponents.stylishtextfields.shimmereffects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldParticleEffect
import com.stylingcomponents.stylishtextfields.models.SpillParticle
import kotlin.math.sin

class LiquidMetalEffect : TextFieldParticleEffect {

    override fun onTextChanged(
        text: String
    ) {
    }

    override fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<SpillParticle>
    ) {

        with(scope) {

            val width = size.width
            val height = size.height

            val sweep =
                width * progress

            drawRoundRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.DarkGray,
                        Color.LightGray,
                        Color.White,
                        Color.LightGray,
                        Color.DarkGray
                    ),
                    startX = sweep - 400f,
                    endX = sweep
                ),
                size = Size(width, height)
            )

            particles.forEach {

                val animatedX =
                    width / 2 +
                            it.velocityX *
                            progress *
                            45f

                val animatedY =
                    height / 2 +
                            sin(progress * 15f) *
                            12f

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White,
                            Color.LightGray,
                            Color.Transparent
                        )
                    ),
                    radius = it.radius * 2f,
                    center = Offset(
                        animatedX,
                        animatedY
                    ),
                    alpha = 1f - progress
                )
            }
        }
    }
}
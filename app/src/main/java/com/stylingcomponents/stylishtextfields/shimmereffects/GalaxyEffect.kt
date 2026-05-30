package com.stylingcomponents.stylishtextfields.shimmereffects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldParticleEffect
import com.stylingcomponents.stylishtextfields.models.SpillParticle

class GalaxyEffect : TextFieldParticleEffect {

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

            // ===================================================
            // GALAXY SWEEP
            // ===================================================

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color(0xFF7B61FF).copy(alpha = 0.4f),
                        Color(0xFF00E5FF).copy(alpha = 0.8f),
                        Color.Transparent
                    ),
                    start = Offset(
                        width * progress - 300f,
                        0f
                    ),
                    end = Offset(
                        width * progress,
                        height
                    )
                ),
                size = Size(width, height)
            )

            // ===================================================
            // STARS
            // ===================================================

            particles.forEach {

                val x =
                    width / 2 +
                            it.velocityX *
                            progress *
                            60f

                val y =
                    height / 2 +
                            it.velocityY *
                            progress *
                            60f

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White,
                            Color.Cyan.copy(alpha = 0.2f)
                        )
                    ),
                    radius = it.radius,
                    center = Offset(x, y),
                    alpha = 1f - progress
                )
            }
        }
    }
}
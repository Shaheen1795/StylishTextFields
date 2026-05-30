package com.stylingcomponents.stylishtextfields.shimmereffects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldParticleEffect
import com.stylingcomponents.stylishtextfields.models.SpillParticle
import kotlin.random.Random

class LightningEffect : TextFieldParticleEffect {

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
            // ELECTRIC FLASH
            // ===================================================

            drawRoundRect(
                color = Color.Cyan.copy(
                    alpha = 0.15f * (1f - progress)
                ),
                size = Size(width, height)
            )

            // ===================================================
            // LIGHTNING STREAKS
            // ===================================================

            repeat(7) {

                val startX =
                    Random.nextFloat() * width

                val startY =
                    Random.nextFloat() * height

                val endX =
                    startX +
                            Random.nextFloat() * 80f -
                            40f

                val endY =
                    startY +
                            Random.nextFloat() * 40f

                drawLine(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White,
                            Color.Cyan,
                            Color.Transparent
                        )
                    ),
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = 4f
                )
            }

            // ===================================================
            // ELECTRIC PARTICLES
            // ===================================================

            particles.forEach {

                val x =
                    width / 2 +
                            it.velocityX *
                            progress *
                            90f

                val y =
                    height / 2 +
                            it.velocityY *
                            progress *
                            90f

                drawCircle(
                    color = Color.Cyan.copy(
                        alpha = 1f - progress
                    ),
                    radius = it.radius,
                    center = Offset(x, y)
                )
            }
        }
    }
}
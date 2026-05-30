package com.stylingcomponents.stylishtextfields.shimmereffects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldParticleEffect
import com.stylingcomponents.stylishtextfields.models.SpillParticle
import kotlin.math.cos
import kotlin.math.sin

class NebulaEffect : TextFieldParticleEffect {

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

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF512DA8).copy(alpha = 0.1f),
                        Color(0xFFE040FB).copy(alpha = 0.6f),
                        Color(0xFF00BCD4).copy(alpha = 0.5f)
                    ),
                    start = Offset.Zero,
                    end = Offset(width, height)
                ),
                size = Size(width, height)
            )

            particles.forEach {

                val x =
                    width / 2 +
                            sin(progress * 10f + it.x) *
                            100f

                val y =
                    height / 2 +
                            cos(progress * 10f + it.y) *
                            40f

                drawCircle(
                    color = Color.Magenta.copy(
                        alpha = 0.2f * (1f - progress)
                    ),
                    radius = it.radius * 4f,
                    center = Offset(x, y)
                )
            }
        }
    }
}
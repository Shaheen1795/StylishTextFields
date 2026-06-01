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
class ParticleRippleBorderEffect :
    BaseBorderEffect(Color.Cyan) {

    override fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<EffectParticle>
    ) {

        with(scope) {

            particles.forEach {

                val x =
                    size.width / 2 +
                            it.velocityX *
                            progress *
                            120f

                val y =
                    size.height / 2 +
                            it.velocityY *
                            progress *
                            120f

                drawCircle(
                    color = borderColor.copy(
                        alpha = 1f - progress
                    ),

                    radius = it.radius,

                    center = Offset(x, y)
                )
            }

            drawMainBorder()
        }
    }
}
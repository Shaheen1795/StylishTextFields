package com.stylingcomponents.stylishtextfields.ripples
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.effects.BaseBorderEffect
import com.stylingcomponents.stylishtextfields.models.EffectParticle

class WaterRippleBorderEffect :
    BaseBorderEffect(Color.Cyan) {

    override fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<EffectParticle>
    ) {

        if (progress >= 0.99f) return

        with(scope) {

            val safeProgress =
                progress.coerceAtLeast(0.01f)

            val alpha =
                1f - safeProgress

            val expansion =
                safeProgress * 140f

            repeat(6) { index ->

                val ripple =
                    expansion + index * 24f

                val ringAlpha =
                    (alpha * (0.7f - index * 0.08f)
                            ).coerceAtLeast(0f)

                drawRoundRect(
                    color = borderColor.copy(
                        alpha = ringAlpha
                    ),

                    topLeft = Offset(
                        -ripple / 2,
                        -ripple / 2
                    ),

                    size = Size(
                        size.width + ripple,
                        size.height + ripple
                    ),

                    cornerRadius = CornerRadius(
                        32.dp.toPx()
                    ),

                    style = Stroke(
                        width = (
                                5f - index * 0.6f
                                ).coerceAtLeast(1f)
                    )
                )
            }

            // ==========================================
            // MAIN BORDER
            // ==========================================

            drawMainBorder()
        }
    }
}

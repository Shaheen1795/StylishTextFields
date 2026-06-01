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
import com.stylingcomponents.stylishtextfields.interfaces.BorderEffect
import com.stylingcomponents.stylishtextfields.models.EffectParticle



class ShockwaveBorderEffect(
) : BaseBorderEffect(Color.Cyan) {

    override fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<EffectParticle>
    ) {

        if (progress >= 0.99f) return

        with(scope) {

            val safeProgress =
                progress.coerceAtLeast(0.01f)

            // =================================================
            // GLOBAL FADE
            // =================================================

            val alpha =
                (1f - safeProgress)
                    .coerceAtLeast(0f)

            // =================================================
            // START BIG → COLLAPSE INSIDE
            // =================================================

            val maxInset = 42f

            repeat(5) { index ->

                // =============================================
                // STAGGERED COLLAPSE
                // =============================================

                val delayedProgress =
                    (
                            safeProgress -
                                    index * 0.08f
                            ).coerceAtLeast(0f)

                // =============================================
                // SHRINK TOWARD CENTER
                // =============================================

                val inset =
                    maxInset * delayedProgress

                // =============================================
                // INDIVIDUAL RING ALPHA
                // =============================================

                val ringAlpha =
                    (
                            alpha *
                                    (
                                            1f -
                                                    index * 0.15f
                                            )
                            ).coerceAtLeast(0f)

                // =============================================
                // STROKE WIDTH DECAY
                // =============================================

                val strokeWidth =
                    (
                            10f -
                                    index * 1.5f
                            ).coerceAtLeast(1.5f)

                drawRoundRect(
                    color = borderColor.copy(
                        alpha = ringAlpha
                    ),

                    topLeft = Offset(
                        inset,
                        inset
                    ),

                    size = Size(
                        size.width - inset * 2,
                        size.height - inset * 2
                    ),

                    cornerRadius = CornerRadius(
                        (
                                30 -
                                        index * 2
                                ).dp.toPx()
                    ),

                    style = Stroke(
                        width = strokeWidth
                    ),

                    blendMode = BlendMode.Screen
                )
            }

            // =================================================
            // CORE BORDER
            // =================================================

            drawMainBorder()
        }
    }
}
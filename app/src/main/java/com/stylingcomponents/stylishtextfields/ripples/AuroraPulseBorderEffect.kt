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

class AuroraPulseBorderEffect :
    BaseBorderEffect(Color.Cyan) {

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
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF00E5FF).copy(alpha),
                        Color(0xFF7C4DFF).copy(alpha),
                        Color(0xFF00C853).copy(alpha),
                        Color(0xFFFF4081).copy(alpha)
                    ),

                    start = Offset.Zero,

                    end = Offset(
                        size.width * progress,
                        size.height
                    )
                ),

                size = size,

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
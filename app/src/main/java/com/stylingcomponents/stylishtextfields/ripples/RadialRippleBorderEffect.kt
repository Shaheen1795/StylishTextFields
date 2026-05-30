package com.stylingcomponents.stylishtextfields.ripples
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloat
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.BorderEffect
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random
class RadialRippleBorderEffect(
    private val borderColor: Color = Color.Cyan
) : BorderEffect {

    override fun draw(
        progress: Float,
        scope: androidx.compose.ui.graphics.drawscope.DrawScope
    ) {

        with(scope) {

            val width = size.width
            val height = size.height

            val rippleExpansion =
                progress * 60f

            val rippleAlpha =
                1f - progress

            // ====================================================
            // OUTER RIPPLE
            // ====================================================

            drawRoundRect(
                brush = Brush.radialGradient(
                    colors = listOf(
                        borderColor.copy(alpha = 0.8f * rippleAlpha),
                        borderColor.copy(alpha = 0.2f * rippleAlpha),
                        Color.Transparent
                    ),
                    center = Offset(
                        width / 2,
                        height / 2
                    ),
                    radius = maxOf(
                        width * progress,
                        1f
                    )
                ),
                topLeft = Offset(
                    -rippleExpansion / 2,
                    -rippleExpansion / 2
                ),
                size = Size(
                    width + rippleExpansion,
                    height + rippleExpansion
                ),
                cornerRadius = CornerRadius(
                    28.dp.toPx()
                ),
                style = Stroke(
                    width = 4.dp.toPx()
                )
            )

            // ====================================================
            // MAIN BORDER
            // ====================================================

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        borderColor.copy(alpha = 0.9f),
                        Color.White.copy(alpha = 0.8f),
                        borderColor.copy(alpha = 0.9f)
                    )
                ),
                size = size,
                cornerRadius = CornerRadius(
                    24.dp.toPx()
                ),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
        }
    }
}


@Composable
fun RippleBorderTextField() {

    val state =
        rememberTextFieldState()

    val rippleAnimation =
        remember {
            Animatable(1f)
        }

    val effect =
        remember {
            RadialRippleBorderEffect()
        }

    // ====================================================
    // TRIGGER RIPPLE WHEN USER TYPES
    // ====================================================

    LaunchedEffect(
        state.text.toString()
    ) {

        if (state.text.isNotEmpty()) {

            rippleAnimation.snapTo(0f)

            rippleAnimation.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 700,
                    easing = LinearEasing
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .padding(24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
        ) {

            // ====================================================
            // EFFECT LAYER
            // ====================================================

            Canvas(
                modifier = Modifier
                    .matchParentSize()
            ) {

                effect.draw(
                    progress = rippleAnimation.value,
                    scope = this
                )
            }

            // ====================================================
            // TEXTFIELD
            // ====================================================

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(2.dp)
                    .clip(
                        RoundedCornerShape(24.dp)
                    )
                    .background(
                        Color(0xFF111111)
                    )
            ) {

                BasicTextField(
                    state = state,
                    textStyle = TextStyle(
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 20.dp
                        ),
                    decorator = { innerTextField ->

                        Box(
                            contentAlignment =
                                Alignment.CenterStart
                        ) {

                            if (state.text.isEmpty()) {

                                Text(
                                    text = "Type something...",
                                    color = Color.Gray
                                )
                            }

                            innerTextField()
                        }
                    }
                )
            }
        }
    }
}
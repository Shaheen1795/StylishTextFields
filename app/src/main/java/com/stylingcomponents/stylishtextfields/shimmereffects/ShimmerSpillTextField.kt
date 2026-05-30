package com.stylingcomponents.stylishtextfields.shimmereffects

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.models.SpillParticle
import com.stylingcomponents.stylishtextfields.models.TrailParticle
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun ShimmerSpillTextField() {

    val state = rememberTextFieldState()

    var particles by remember {
        mutableStateOf<List<SpillParticle>>(emptyList())
    }

    val shimmerProgress = remember {
        Animatable(0f)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(state.text.toString()) {

        if (state.text.isNotEmpty()) {

            particles = buildList {

                repeat(40) {

                    val angle = Random.nextFloat() * 360f
                    val speed = Random.nextFloat() * 10f + 4f

                    add(
                        SpillParticle(
                            id = it,
                            x = 0f,
                            y = 0f,
                            radius = Random.nextFloat() * 2f + 4f,
                            velocityX = cos(angle) * speed,
                            velocityY = sin(angle) * speed,
                            alpha = 1f
                        )
                    )
                }
            }

            shimmerProgress.snapTo(0f)

            shimmerProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1400,
                    easing = LinearEasing
                )
            )

            particles = emptyList<SpillParticle>()
        }
    }

    Box(
        modifier = Modifier
            .padding(24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(Color(0xFF111111))
        ) {

            val shimmerOffset by remember {
                derivedStateOf {
                    shimmerProgress.value
                }
            }

            Canvas(
                modifier = Modifier.matchParentSize()
            ) {

                val width = size.width
                val height = size.height

                val shimmerX = width * shimmerOffset

                drawRoundRect(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.7f),
                            Color.Cyan.copy(alpha = 0.9f),
                            Color.Transparent
                        ),
                        start = Offset(shimmerX - 200f, 0f),
                        end = Offset(shimmerX, height)
                    ),
                    size = Size(width, height)
                )

                particles.forEach { particle ->

                    val animatedX =
                        particle.x + particle.velocityX * shimmerOffset * 50f

                    val animatedY =
                        particle.y + particle.velocityY * shimmerOffset * 50f

                    drawCircle(
                        color = Color.Cyan.copy(
                            alpha = (1f - shimmerOffset)
                        ),
                        radius = particle.radius,
                        center = Offset(
                            x = width / 2 + animatedX,
                            y = height / 2 + animatedY
                        )
                    )
                }
            }

            BasicTextField(
                state = state,
                textStyle = TextStyle(
                    color = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 20.dp),
                decorator = { innerTextField ->

                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {

                        if (state.text.isEmpty()) {

                            Text(
                                text = "Type something...",
                                color = Color.White.copy(alpha = 0.4f)
                            )
                        }

                        innerTextField()
                    }
                }
            )
        }
    }
}
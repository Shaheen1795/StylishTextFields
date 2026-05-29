package com.stylingcomponents.stylishtextfields.trailrenderers

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.models.TrailParticle
import kotlinx.coroutines.launch

class ShimmerTrailRenderer {

    private var nextId = 0

    private val particles =
        mutableStateListOf<TrailParticle>()

    fun emit() {

        particles.add(

            TrailParticle(
                id = nextId++,
                offsetX = 0f,
                alpha = 1f
            )
        )
    }

    @Composable
    fun Render() {

        val density = LocalDensity.current

        LaunchedEffect(
            particles.size
        ) {

            particles.forEach { particle ->

                launch {

                    animate(

                        initialValue = 0f,

                        targetValue = 80f,

                        animationSpec = tween(600)
                    ) { value, _ ->

                        val index =
                            particles.indexOfFirst {
                                it.id == particle.id
                            }

                        if (index != -1) {

                            particles[index] =
                                particle.copy(

                                    offsetX = value,

                                    alpha = 1f - (value / 80f)
                                )
                        }
                    }

                    particles.removeAll {
                        it.id == particle.id
                    }
                }
            }
        }

        particles.forEach { particle ->

            Box(
                modifier = Modifier

                    .offset {

                        IntOffset(
                            particle.offsetX.toInt(),
                            0
                        )
                    }

                    .graphicsLayer {
                        alpha = particle.alpha
                    }

                    .width(40.dp)

                    .height(4.dp)

                    .background(

                        brush = Brush.horizontalGradient(

                            listOf(
                                Color.Cyan,
                                Color.Transparent
                            )
                        ),

                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}
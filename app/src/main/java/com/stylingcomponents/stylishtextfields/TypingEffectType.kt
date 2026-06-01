@file:OptIn(ExperimentalTextApi::class)

package com.stylingcomponents.stylishtextfields

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.SmartBorderEffect
import com.stylingcomponents.stylishtextfields.ripples.NeonPlasmaBorderEffect
import com.stylingcomponents.stylishtextfields.ripples.ShaderEnergyBorderEffect
import com.stylingcomponents.stylishtextfields.ripples.ShockwaveBorderEffect
import com.stylingcomponents.stylishtextfields.ripples.WaterRippleBorderEffect
import kotlinx.coroutines.delay

// ========================================================
// EFFECT TYPE
// ========================================================

enum class TypingEffectType {

    WATER_RIPPLE,
    LIGHTNING,
    PLASMA,
    REVERSE_COLLAPSE
}

// ========================================================
// EFFECT ENGINE
// ========================================================

class SmartTypingEffectEngine {

    private var lastTypingTime = 0L

    private var continuousTypingCount = 0

    fun detectEffect(
        oldText: String,
        newText: String
    ): TypingEffectType {

        val currentTime =
            System.currentTimeMillis()

        val typingDiff =
            currentTime - lastTypingTime

        // ============================================
        // DELETE DETECTION
        // ============================================

        if (newText.length < oldText.length) {

            continuousTypingCount = 0

            lastTypingTime = currentTime

            return TypingEffectType.REVERSE_COLLAPSE
        }

        // ============================================
        // CONTINUOUS BURST DETECTION
        // ============================================

        if (typingDiff < 450) {

            continuousTypingCount++

        } else {

            continuousTypingCount = 1
        }

        lastTypingTime = currentTime

        // ============================================
        // PLASMA MODE
        // ============================================

        if (continuousTypingCount >= 6) {

            return TypingEffectType.PLASMA
        }

        // ============================================
        // LIGHTNING MODE
        // ============================================

        if (continuousTypingCount >= 3) {

            return TypingEffectType.LIGHTNING
        }

        // ============================================
        // DEFAULT
        // ============================================

        return TypingEffectType.WATER_RIPPLE
    }
}



// ========================================================
// WATER EFFECT
// ========================================================

class WaterRippleEffect :
    SmartBorderEffect {

    override fun draw(
        scope: DrawScope,
        progress: Float
    ) {

        WaterRippleBorderEffect()
            .draw(
                scope,
                progress
            )
    }
}

// ========================================================
// LIGHTNING EFFECT
// ========================================================

class LightningBorderEffect :
    SmartBorderEffect {

    override fun draw(
        scope: DrawScope,
        progress: Float
    ) {

        ShaderEnergyBorderEffect()
            .draw(
                scope,
                progress
            )
    }
}

// ========================================================
// PLASMA EFFECT
// ========================================================

class PlasmaBorderEffect :
    SmartBorderEffect {

    override fun draw(
        scope: DrawScope,
        progress: Float
    ) {

        NeonPlasmaBorderEffect()
            .draw(
                scope,
                progress
            )
    }
}

// ========================================================
// REVERSE COLLAPSE EFFECT
// ========================================================

class ReverseCollapseEffect :
    SmartBorderEffect {

    override fun draw(
        scope: DrawScope,
        progress: Float
    ) {

        ShockwaveBorderEffect()
            .draw(
                scope,
                1f - progress
            )
    }
}

// ========================================================
// SMART TEXTFIELD
// ========================================================

@Composable
fun SmartReactiveTextField() {

    val state =
        rememberTextFieldState()

    val animation =
        remember {
            Animatable(1f)
        }

    val engine =
        remember {
            SmartTypingEffectEngine()
        }

    var oldText by remember {
        mutableStateOf("")
    }

    var currentEffect by remember {

        mutableStateOf<SmartBorderEffect>(
            WaterRippleEffect()
        )
    }

    // ====================================================
    // TEXT CHANGE DETECTION
    // ====================================================

    LaunchedEffect(
        state.text.toString()
    ) {

        val newText =
            state.text.toString()

        if (newText != oldText) {

            val effectType =
                engine.detectEffect(
                    oldText,
                    newText
                )
            Log.d("SmartTextField", "Effect type: $effectType")

            currentEffect =
                when(effectType) {

                    TypingEffectType.WATER_RIPPLE ->
                        WaterRippleEffect()

                    TypingEffectType.LIGHTNING ->
                        LightningBorderEffect()

                    TypingEffectType.PLASMA ->
                        PlasmaBorderEffect()

                    TypingEffectType.REVERSE_COLLAPSE ->
                        ReverseCollapseEffect()
                }

            oldText = newText

            animation.snapTo(0f)

            animation.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
        }
    }

    // ====================================================
    // UI
    // ====================================================

    Box(
        modifier = Modifier
            .padding(24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {

            // ================================================
            // EFFECT LAYER
            // ================================================

            Canvas(
                modifier = Modifier
                    .matchParentSize()
            ) {

                currentEffect.draw(
                    scope = this,
                    progress = animation.value
                )
            }

            // ================================================
            // TEXTFIELD
            // ================================================

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(6.dp)
                    .clip(
                        RoundedCornerShape(22.dp)
                    )
                    .background(
                        Color.Black.copy(alpha = 0.92f)
                    )
            ){
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
                                    text =
                                        "Start typing...",
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

// ========================================================
// PREVIEW
// ========================================================

@Preview(
    showBackground = true,
    backgroundColor =0xFF000000
)
@Composable
fun SmartReactiveTextFieldPreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        SmartReactiveTextField()
    }
}
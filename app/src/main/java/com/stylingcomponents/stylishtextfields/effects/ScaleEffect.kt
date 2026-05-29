package com.stylingcomponents.stylishtextfields.effects

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldEffect

class ScaleEffect : TextFieldEffect {

    @Composable
    override fun modifier(
        isFocused: Boolean
    ): Modifier {

        val scale by animateFloatAsState(
            targetValue = if (isFocused) 1.03f else 1f,
            label = ""
        )

        return Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
    }
}
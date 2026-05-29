package com.stylingcomponents.stylishtextfields.effects

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldEffect

class GlowEffect : TextFieldEffect {

    @Composable
    override fun modifier(
        isFocused: Boolean
    ): Modifier {

        return Modifier.shadow(
            elevation = if (isFocused) 30.dp else 0.dp,
            ambientColor = Color.Cyan,
            spotColor = Color.Cyan
        )
    }
}
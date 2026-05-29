package com.stylingcomponents.stylishtextfields.styles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldStyle

class MatrixTextFieldStyle : TextFieldStyle {

    @Composable
    override fun containerModifier(
        isFocused: Boolean
    ): Modifier {

        return Modifier
            .border(
                width = 2.dp,
                color = Color.Green,
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(
                elevation = 20.dp,
                ambientColor = Color.Green,
                spotColor = Color.Green
            )
    }

    @Composable
    override fun colors(): TextFieldColors {

        return TextFieldDefaults.colors(
            focusedContainerColor = Color.Black,
            unfocusedContainerColor = Color.Black,
            focusedTextColor = Color.Green,
            cursorColor = Color.Green,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    }
}
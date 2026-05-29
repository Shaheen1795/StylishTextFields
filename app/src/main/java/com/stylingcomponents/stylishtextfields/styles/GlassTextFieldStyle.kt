package com.stylingcomponents.stylishtextfields.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldStyle

class GlassTextFieldStyle : TextFieldStyle {

    @Composable
    override fun containerModifier(
        isFocused: Boolean
    ): Modifier {

        return Modifier
            .background(
                Color.White.copy(alpha = 0.1f),
                RoundedCornerShape(20.dp)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.2f),
                RoundedCornerShape(20.dp)
            )
    }

    @Composable
    override fun colors(): TextFieldColors {

        return TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    }
}
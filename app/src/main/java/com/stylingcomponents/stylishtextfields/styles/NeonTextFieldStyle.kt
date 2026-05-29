package com.stylingcomponents.stylishtextfields.styles

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldStyle

class NeonTextFieldStyle : TextFieldStyle {

    @Composable
    override fun containerModifier(
        isFocused: Boolean
    ): Modifier {

        return Modifier
            .shadow(
                elevation = if (isFocused) 20.dp else 8.dp,
                ambientColor = Color.Cyan,
                spotColor = Color.Magenta
            )
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(
                        Color.Cyan,
                        Color.Magenta
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
    }

    @Composable
    override fun colors(): TextFieldColors {

        return TextFieldDefaults.colors(
            focusedContainerColor = Color.Black,
            unfocusedContainerColor = Color.Black,
            focusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    }
}
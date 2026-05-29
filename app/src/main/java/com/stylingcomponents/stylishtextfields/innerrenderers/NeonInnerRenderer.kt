package com.stylingcomponents.stylishtextfields.innerrenderers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.interfaces.InnerTextFieldRenderer

class NeonInnerRenderer : InnerTextFieldRenderer {

    @Composable
    override fun Content(
        value: String,
        innerTextField: @Composable () -> Unit,
        isFocused: Boolean,
        hint: String
    ) {

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFF111111),
                            Color(0xFF1A1A1A)
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {

            if (value.isEmpty()) {

                Text(
                    text = hint,
                    color = Color.Gray
                )
            }

            innerTextField()
        }
    }
}
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

class GlassInnerRenderer : InnerTextFieldRenderer {

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
                    Color.White.copy(alpha = 0.08f),
                    RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
        ) {

            if (value.isEmpty()) {

                Text(
                    hint,
                    color = Color.White.copy(alpha = 0.5f)
                )
            }

            innerTextField()
        }
    }
}
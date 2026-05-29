package com.stylingcomponents.stylishtextfields.interfaces

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue



    interface CursorRenderer {

        @Composable
        fun brush(
            isFocused: Boolean
        ): Brush
    }

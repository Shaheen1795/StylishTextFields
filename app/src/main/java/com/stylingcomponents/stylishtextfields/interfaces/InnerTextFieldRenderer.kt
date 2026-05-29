package com.stylingcomponents.stylishtextfields.interfaces

import androidx.compose.runtime.Composable

interface InnerTextFieldRenderer {

    @Composable
    fun Content(
        value: String,
        innerTextField: @Composable () -> Unit,
        isFocused: Boolean,
        hint: String
    )
}
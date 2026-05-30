package com.stylingcomponents.stylishtextfields.interfaces

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface TextFieldEffect {

    @Composable
    fun modifier(
        isFocused: Boolean
    ): Modifier
}



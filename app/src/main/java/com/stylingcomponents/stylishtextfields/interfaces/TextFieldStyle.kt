package com.stylingcomponents.stylishtextfields.interfaces

import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface TextFieldStyle {

    @Composable
    fun containerModifier(
        isFocused: Boolean
    ): Modifier

    @Composable
    fun colors(): TextFieldColors
}
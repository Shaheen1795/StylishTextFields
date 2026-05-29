package com.stylingcomponents.stylishtextfields.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TextFieldState {

    var text by mutableStateOf("")
    var isFocused by mutableStateOf(false)
}
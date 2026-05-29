package com.stylingcomponents.stylishtextfields.models

data class TextFieldConfig(

    val hint: String,
    val maxLength: Int = Int.MAX_VALUE,
    val singleLine: Boolean = true,
    val readOnly: Boolean = false
)
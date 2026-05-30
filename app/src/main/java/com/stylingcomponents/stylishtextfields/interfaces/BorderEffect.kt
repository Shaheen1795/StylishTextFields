package com.stylingcomponents.stylishtextfields.interfaces
interface BorderEffect {

    fun draw(
        progress: Float,
        scope: androidx.compose.ui.graphics.drawscope.DrawScope
    )
}
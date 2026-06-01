package com.stylingcomponents.stylishtextfields.interfaces

import androidx.compose.ui.graphics.drawscope.DrawScope

interface SmartBorderEffect {

    fun draw(
        scope: DrawScope,
        progress: Float
    )
}
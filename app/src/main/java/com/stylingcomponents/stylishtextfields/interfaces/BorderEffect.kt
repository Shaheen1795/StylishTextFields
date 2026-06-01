package com.stylingcomponents.stylishtextfields.interfaces

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.stylingcomponents.stylishtextfields.models.EffectParticle

interface BorderEffect {

    fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<EffectParticle> = emptyList()
    )
}
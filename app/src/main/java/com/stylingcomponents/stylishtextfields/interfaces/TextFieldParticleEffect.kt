package com.stylingcomponents.stylishtextfields.interfaces

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.stylingcomponents.stylishtextfields.models.SpillParticle

interface TextFieldParticleEffect {

    fun onTextChanged(
        text: String
    )

    fun draw(
        scope: DrawScope,
        progress: Float,
        particles: List<SpillParticle>
    )
}
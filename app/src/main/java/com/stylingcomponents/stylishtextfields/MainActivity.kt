package com.stylingcomponents.stylishtextfields

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stylingcomponents.stylishtextfields.cursors.NeonCursorRenderer
import com.stylingcomponents.stylishtextfields.effects.GlowEffect
import com.stylingcomponents.stylishtextfields.effects.ScaleEffect
import com.stylingcomponents.stylishtextfields.innerrenderers.GlassInnerRenderer
import com.stylingcomponents.stylishtextfields.innerrenderers.NeonInnerRenderer
import com.stylingcomponents.stylishtextfields.interfaces.CursorRenderer
import com.stylingcomponents.stylishtextfields.interfaces.InnerTextFieldRenderer
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldEffect
import com.stylingcomponents.stylishtextfields.interfaces.TextFieldStyle
import com.stylingcomponents.stylishtextfields.models.TextFieldConfig
import com.stylingcomponents.stylishtextfields.states.TextFieldState
import com.stylingcomponents.stylishtextfields.styles.AuroraTextFieldStyle
import com.stylingcomponents.stylishtextfields.styles.CosmicTextFieldStyle
import com.stylingcomponents.stylishtextfields.styles.FireTextFieldStyle
import com.stylingcomponents.stylishtextfields.styles.FrostedTextFieldStyle
import com.stylingcomponents.stylishtextfields.styles.GlassTextFieldStyle
import com.stylingcomponents.stylishtextfields.styles.MatrixTextFieldStyle
import com.stylingcomponents.stylishtextfields.styles.NeonTextFieldStyle
import com.stylingcomponents.stylishtextfields.ui.theme.StylishTextFieldsTheme
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.stylingcomponents.stylishtextfields.cursors.AuroraCursorRenderer
import com.stylingcomponents.stylishtextfields.cursors.FireCursorRenderer
import com.stylingcomponents.stylishtextfields.cursors.GlitchCursorRenderer
import com.stylingcomponents.stylishtextfields.cursors.IceCursorRenderer
import com.stylingcomponents.stylishtextfields.cursors.MatrixCursorRenderer
import com.stylingcomponents.stylishtextfields.trailrenderers.ShimmerTrailRenderer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StylishTextFieldsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   PreviewScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}




// =====================================================
// STATE
// =====================================================

class TextFieldState {

    var value by mutableStateOf(
        TextFieldValue("")
    )

    var isFocused by mutableStateOf(false)
}

// =====================================================
// CURSOR RENDERER
// =====================================================

interface CursorRenderer {

    @Composable
    fun Cursor(

        textFieldValue: TextFieldValue,

        textLayoutResult: TextLayoutResult?,

        isFocused: Boolean
    )
}


// =====================================================
// TEXT FIELD
// =====================================================

@Composable
fun NeonTextField(

    state: TextFieldState,

    cursorRenderer: CursorRenderer,

    trailRenderer: ShimmerTrailRenderer,

    hint: String = "Cyberpunk Input"
) {

    BasicTextField(

        value = state.text,

        onValueChange = {
            state.text = it
            trailRenderer.emit()
        },

        singleLine = true,

        textStyle = TextStyle(
            color = Color.White,
            fontSize = 18.sp
        ),

        cursorBrush = cursorRenderer.brush(
            state.isFocused
        ),

        modifier = Modifier

            .fillMaxWidth()

            .border(
                width = 2.dp,

                brush = Brush.linearGradient(
                    listOf(
                        Color.Cyan,
                        Color.Magenta
                    )
                ),

                shape = RoundedCornerShape(24.dp)
            )

            .background(
                Color.Black,
                RoundedCornerShape(24.dp)
            )

            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )

            .onFocusChanged {
                state.isFocused = it.isFocused
            },

        decorationBox = { innerTextField ->

            Box {

                if (state.text.isEmpty()) {

                    Text(
                        text = hint,
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                }
                innerTextField()
            }
        }
    )
}

// =====================================================
// DEMO SCREEN
// =====================================================

@Composable
fun DemoScreen() {

    val state = remember {
        TextFieldState()
    }

    val cursorRenderer = remember {
        NeonCursorRenderer()
    }

    Box(
        modifier = Modifier
            .background(Color(0xFF050505))
            .padding(20.dp)
    ) {

        NeonTextField(

            state = state,

            cursorRenderer = cursorRenderer,

            ShimmerTrailRenderer(),

            hint = "Cyberpunk Input"
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF050505
)
@Composable
fun PreviewScreen() {

    val state = remember {
        TextFieldState()
    }

    var currentCursor by remember {
        mutableStateOf(0)
    }

    val cursorRenderer = remember(currentCursor) {

        when(currentCursor) {

            0 -> NeonCursorRenderer()

            1 -> AuroraCursorRenderer()

            2 -> MatrixCursorRenderer()

            3 -> FireCursorRenderer()

            4 -> IceCursorRenderer()

            else -> GlitchCursorRenderer()
        }
    }

    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(
                Color(0xFF050505)
            )

            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        NeonTextField(

            state = state,

            cursorRenderer = cursorRenderer,

            ShimmerTrailRenderer(),

            hint = "Cyberpunk Input"
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Button(
                onClick = {
                    currentCursor = 0
                }
            ) {
                Text("Neon")
            }

            Button(
                onClick = {
                    currentCursor = 1
                }
            ) {
                Text("Aurora")
            }

            Button(
                onClick = {
                    currentCursor = 2
                }
            ) {
                Text("Matrix")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Button(
                onClick = {
                    currentCursor = 3
                }
            ) {
                Text("Fire")
            }

            Button(
                onClick = {
                    currentCursor = 4
                }
            ) {
                Text("Ice")
            }

            Button(
                onClick = {
                    currentCursor = 5
                }
            ) {
                Text("Glitch")
            }
        }
    }
}
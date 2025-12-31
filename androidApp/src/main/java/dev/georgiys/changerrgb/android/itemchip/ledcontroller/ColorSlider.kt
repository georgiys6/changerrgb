package dev.georgiys.changerrgb.android.itemchip.ledcontroller

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ColorSlider(
    label: String,
    value: Float,
    tint: Color,
    onValueChange: (Float) -> Unit
) {
    Column {
        Text("$label: ${value.toInt()}")
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..255f,
            colors = SliderDefaults.colors(
                thumbColor = tint,
                activeTrackColor = tint
            )
        )
    }
}

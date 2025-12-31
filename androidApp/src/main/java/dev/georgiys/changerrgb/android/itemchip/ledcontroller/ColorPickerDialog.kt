package dev.georgiys.changerrgb.android.itemchip.ledcontroller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPickerDialog(
    initialColor: Color,
    onDismiss: () -> Unit,
    onConfirm: (Color) -> Unit
) {
    var red by remember { mutableFloatStateOf(initialColor.red * 255f) }
    var green by remember { mutableFloatStateOf(initialColor.green * 255f) }
    var blue by remember { mutableFloatStateOf(initialColor.blue * 255f) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выбор цвета") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                ColorSlider("R", red, Color.Red) { red = it }
                ColorSlider("G", green, Color.Green) { green = it }
                ColorSlider("B", blue, Color.Blue) { blue = it }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Color(
                                red = red.toInt(),
                                green = green.toInt(),
                                blue = blue.toInt()
                            )
                        )
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(
                        Color(
                            red = red.toInt(),
                            green = green.toInt(),
                            blue = blue.toInt()
                        )
                    )
                }
            ) {
                Text("ОК")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}

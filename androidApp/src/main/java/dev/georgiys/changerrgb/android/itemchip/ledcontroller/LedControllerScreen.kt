package dev.georgiys.changerrgb.android.itemchip.ledcontroller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.data.data.list.Mode
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LedControllerScreen(
    ledController: LedController,
    setStateToLed: (LedController) -> Unit,
    setSpeedToLed: (LedController) -> Unit,
    setBrightnessToLed: (LedController) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var brightness by remember { mutableFloatStateOf(ledController.brightness) }
    var speed by remember { mutableFloatStateOf(ledController.speed) }

    val modes = listOf(
        Mode("Радуга","RAINBOW", 1),
        Mode("Бегущий огонь","RUNSTR", 2),
        Mode("Бегущие огни","STROBE", 3),
        Mode("Одиночные огни(Случайные)","RANDOMLIGHT", 4),
        Mode("Вспышки","SPARK", 5),
        Mode("Смена цвета","FADE", 6),
        Mode("Бегущий огонь 2","RUNLIGHT", 7),
        Mode("Хаос","CHAOS", 8),
        Mode("2 бегущих огня","RUNSTR2", 9),
        Mode("Статичный цвет","STATIC", 249),
        Mode("Выкл.","RUNSTR2", 250),
    )
    var selectedMode by remember { mutableStateOf(modes.firstOrNull  { it.idCommand == ledController.mode } ?: modes.last()) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(speed) {
        coroutineScope.launch {
            delay(500)
            if (speed != ledController.speed) {
                ledController.speed = speed
                setSpeedToLed(ledController)
            }
        }
    }

    LaunchedEffect(brightness) {
        coroutineScope.launch {
            delay(500)
            if (brightness != ledController.brightness) {
                ledController.brightness = brightness
                setBrightnessToLed(ledController)
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFAAD8FF))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                SliderBlock(
                    modifier = Modifier.weight(1f),
                    icon = "💡",
                    title = "Яркость",
                    value = brightness,
                    onValueChange = { brightness = it },
                    valueRange = 0f..100f,
                    steps = 99
                )

                SliderBlock(
                    modifier = Modifier.weight(1f),
                    icon = "⚡",
                    title = "Скорость",
                    value = speed,
                    onValueChange = { speed = it },
                    valueRange = 0f..20f,
                    steps = 19
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                ColorPreview(
                    modifier = Modifier.weight(1f),
                    color = Color(
                        red = ledController.colorR,
                        green = ledController.colorG,
                        blue = ledController.colorB,
                    ),
                    onColorSelected = { color ->
                        ledController.colorR = (color.red*255).toInt()
                        ledController.colorG = (color.green*255).toInt()
                        ledController.colorB = (color.blue*255).toInt()
                        setStateToLed(ledController)
                    }
                )

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("✨", fontSize = 18.sp)
                        Spacer(Modifier.width(6.dp))
                        Text("Режим", fontWeight = FontWeight.Medium)
                    }

                    Spacer(Modifier.height(6.dp))

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        TextField(
                            value = selectedMode.title,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            modes.forEach { mode ->
                                DropdownMenuItem(
                                    text = { Text(mode.title) },
                                    onClick = {
                                        selectedMode = mode
                                        ledController.mode = mode.idCommand
                                        expanded = false
                                        setStateToLed(ledController)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
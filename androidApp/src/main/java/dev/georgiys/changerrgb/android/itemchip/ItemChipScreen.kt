package dev.georgiys.changerrgb.android.itemchip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.georgiys.changerrgb.android.itemchip.ledcontroller.LedControllerScreen
import dev.georgiys.changerrgb.android.itemchip.telemetry.TelemetryScreen
import dev.georgiys.changerrgb.android.itemchip.viewstate.ItemChipScreenState
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.data.data.Telemetry
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

@Composable
fun ItemChipScreen(
    state: ItemChipScreenState,
    setStateToLed: (LedController) -> Unit,
    setSpeedToLed: (LedController) -> Unit,
    setBrightnessToLed: (LedController) -> Unit,
    setNewChipName: () -> Unit,
    onChipNameChange: (String) -> Unit,
    onAxisClick: (Long, String) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.chipName,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    onValueChange = onChipNameChange,
                    label = { Text("Название устройства") },
                    singleLine = true
                )

                Button(
                    onClick = {
                        setNewChipName()
                    }
                ) {
                    Text(text = "Изменить")
                }
            }

            Box(Modifier.fillMaxSize()) {
                when {
                    state.loading -> {
                        ItemChipLoading()
                    }

                    state.item == null -> {
                        ItemChipEmpty()
                    }

                    else -> {
                        when (state.type) {
                            "Telemetry" ->
                                TelemetryScreen(
                                    state.item?.let {
                                        Json.decodeFromJsonElement<Telemetry>(it)
                                    },
                                    onAxisClick
                                )

                            "LedController" ->
                                LedControllerScreen(
                                    state.item.let {
                                        Json.decodeFromJsonElement<LedController>(it!!)
                                    },
                                    setStateToLed,
                                    setSpeedToLed,
                                    setBrightnessToLed
                                )
                        }
                    }
                }
                state.errorMessage?.let {
                    Snackbar(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                    ) {
                        Text(it)
                    }
                }

                if (state.loading) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

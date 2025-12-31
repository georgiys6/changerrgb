package dev.georgiys.changerrgb.android.itemchip.telemetry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DeviceThermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.georgiys.changerrgb.data.data.Telemetry

@Composable
fun TelemetryScreen(
    telemetry: Telemetry?,
    onAxisClick: (Long, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InfoCard(
            modifier = Modifier.weight(1f),
            indicatorColor = Color(0xFFFF4D4D),
            backgroundColor = Color(0xFFFFE3E3),
            icon = Icons.Filled.DeviceThermostat,
            onClick = onAxisClick,
            chipId = telemetry?.chipId ?: -1,
            typeDevice = "Temperature",
            title = "Температура",
            value = "${telemetry?.data[0]?.temperature} °C"
        )

        InfoCard(
            modifier = Modifier.weight(1f),
            indicatorColor = Color(0xFF2196F3),
            backgroundColor = Color(0xFFE3F5FF),
            icon = Icons.Filled.WaterDrop,
            onClick = onAxisClick,
            chipId = telemetry?.chipId ?: -1,
            typeDevice = "Humidity",
            title = "Влажность",
            value = "${telemetry?.data[0]?.humidity} %"
        )

        InfoCard(
            modifier = Modifier.weight(1f),
            indicatorColor = Color(0xFF4CAF50),
            backgroundColor = Color(0xFFE9FFE3),
            icon = Icons.Filled.Cloud,
            onClick = onAxisClick,
            chipId = telemetry?.chipId ?: -1,
            typeDevice = "CO2ppm",
            title = "CO2",
            value = "${telemetry?.data[0]?.co2ppm} ppm",
            subtitle = "Допустимое качество воздуха"
        )
    }
}
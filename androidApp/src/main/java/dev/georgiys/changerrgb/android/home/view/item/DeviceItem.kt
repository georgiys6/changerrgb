package dev.georgiys.changerrgb.android.home.view.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.georgiys.changerrgb.data.data.Device
import dev.georgiys.changerrgb.domain.usecase.GetDeviceStateUseCase

@Composable
fun DeviceItem(
    device: Device,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = device.deviceName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Тип: ${device.typeDevice}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Chip ID: ${device.chipId}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

//                // Иконка в зависимости от типа устройства (опционально)
//                Icon(
//                    imageVector = when (device.typeDevice.lowercase()) {
//                        "sensor" -> Icons.Default.Sensors
//                        "switch" -> Icons.Default.Power
//                        "light" -> Icons.Default.Lightbulb
//                        else -> Icons.Default.Devices
//                    },
//                    contentDescription = "Тип устройства",
//                    tint = MaterialTheme.colorScheme.primary
//                )
            }
        }
    }
}

// Пример данных для preview
@Preview
@Composable
fun DeviceItemPreview() {
    MaterialTheme {
        DeviceItem(
            device = Device(
                chipId = 1234567890,
                typeDevice = "Sensor",
                deviceName = "Датчик температуры в гостиной"
            ),
        )
    }
}

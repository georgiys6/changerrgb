package dev.georgiys.changerrgb.android.itemchip.ledcontroller

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColorPreview(
    modifier: Modifier = Modifier,
    color: Color,
    onColorSelected: (Color) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("🎨", fontSize = 18.sp)
            Spacer(Modifier.width(6.dp))
            Text("Цвет", fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color)
                .clickable { showDialog = true }
        )
    }

    if (showDialog) {
        ColorPickerDialog(
            initialColor = color,
            onDismiss = { showDialog = false },
            onConfirm = {
                onColorSelected(it)
                showDialog = false
            }
        )
    }
}


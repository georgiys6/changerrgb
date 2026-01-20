package dev.georgiys.changerrgb.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Red, // Используем Red как акцентный цвет, чтобы его было видно
            onPrimary = Color.White,
            secondary = Red,
            onSecondary = Color.White,
            surface = Dark2,
            onSurface = Color.White,
            tertiary = Color(0xFFBB86FC), // Светло-фиолетовый для контраста
            background = Dark1,
            onBackground = Color.White
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE),
            onPrimary = Color.White,
            secondary = Color(0xFF03DAC5),
            onSecondary = Color.Black,
            tertiary = Color(0xFF3700B3)
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

val Dark1 = Color(red = 22, green = 23, blue = 29)
val Dark2 = Color(red = 28, green = 29, blue = 35)
val Dark3 = Color(red = 31, green = 31, blue = 36)
val Dark4 = Color(red = 34, green = 35, blue = 40)
val Red = Color(red = 220, green = 0, blue = 59)

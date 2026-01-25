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
            primary = Red,
            onPrimary = Color.White,

            background = Dark1,
            onBackground = Color.White,

            surface = Dark2,
            onSurface = Color.White,

            surfaceVariant = Dark3,
            onSurfaceVariant = Color(0xFFCAC4D0),

            error = Red,
            onError = Color.Black,

            errorContainer = Color(0xFF4A0C0C),
            onErrorContainer = Color(0xFFFFB4AB),

            surfaceContainerLowest = Dark1,
            surfaceContainerLow = Dark2,
            surfaceContainer = Dark3,
            surfaceContainerHigh = Dark4,
        )
    } else {
        LightColors
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

val LightColors = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,

    secondary = Color(0xFF03DAC5),
    onSecondary = Color.Black,

    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),

    error = Color(0xFFB3261E),
    onError = Color.White,

    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),

    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
)


val Dark1 = Color(red = 22, green = 23, blue = 29)
val Dark2 = Color(red = 28, green = 29, blue = 35)
val Dark3 = Color(red = 31, green = 31, blue = 36)
val Dark4 = Color(red = 34, green = 35, blue = 40)
val Red = Color(red = 220, green = 0, blue = 59)

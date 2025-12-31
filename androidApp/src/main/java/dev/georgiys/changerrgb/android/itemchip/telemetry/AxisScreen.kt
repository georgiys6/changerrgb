package dev.georgiys.changerrgb.android.itemchip.telemetry

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import dev.georgiys.changerrgb.data.data.list.ChartPoint

@Composable
fun AxisScreen(state: AxisScreenState) {

    when {
        state.loading -> {
            CircularProgressIndicator()
        }

        state.errorMessage != null -> {
            Text("Ошибка: ${state.errorMessage}")
        }

        else -> {
            AxisChartScreen(state)
        }
    }
}
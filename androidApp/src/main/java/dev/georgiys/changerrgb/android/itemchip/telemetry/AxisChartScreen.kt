package dev.georgiys.changerrgb.android.itemchip.telemetry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.compose.common.vicoTheme
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.core.common.data.ExtraStore
import dev.georgiys.changerrgb.data.data.list.CO2ppmItemList
import dev.georgiys.changerrgb.data.data.list.HumidityItemList
import dev.georgiys.changerrgb.data.data.list.TemperatureItemList
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

private val BottomAxisLabelKey = ExtraStore.Key<List<String>>()

private val BottomAxisValueFormatter = CartesianValueFormatter { context, x, _ ->
    context.model.extraStore[BottomAxisLabelKey][x.toInt()]
}

@Composable
fun AxisChartScreen(
    state: AxisScreenState
){
    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            when (state.typeDevice) {
                "Temperature" -> {
                    val list = state.points.map { Json.decodeFromJsonElement<TemperatureItemList>(it) }
                    val map: Map<String, Double> = list.associate {
                        it.time to it.temperature
                    }
                    lineSeries  { series(map.values) }
                    extras { it[BottomAxisLabelKey] = map.keys.toList() }
                }
                "Humidity" -> {
                    val list = state.points.map { Json.decodeFromJsonElement<HumidityItemList>(it) }
                    val map: Map<String, Double> = list.associate {
                        it.time to it.humidity
                    }
                    lineSeries  { series(map.values) }
                    extras { it[BottomAxisLabelKey] = map.keys.toList() }
                }
                "CO2ppm" -> {
                    val list = state.points.map { Json.decodeFromJsonElement<CO2ppmItemList>(it) }
                    val map: Map<String, Double> = list.associate {
                        it.time to it.cO2ppm
                    }
                    lineSeries  { series(map.values) }
                    extras { it[BottomAxisLabelKey] = map.keys.toList() }
                }
            }
        }
    }

    CartesianChartHost(
        rememberCartesianChart(
            rememberLineCartesianLayer(
                LineCartesianLayer.LineProvider.series(
                    vicoTheme.lineCartesianLayerColors.map { color ->
                        LineCartesianLayer.rememberLine(
                            LineCartesianLayer.LineFill.single(fill(color))
                        )
                    }
                )
            ),
            startAxis = VerticalAxis.rememberStart(
                label = rememberTextComponent(
                    color = Color.Black
                )
            ),
            bottomAxis = HorizontalAxis.rememberBottom(
                itemPlacer = remember { HorizontalAxis.ItemPlacer.segmented() },
                valueFormatter = BottomAxisValueFormatter,
                label = rememberTextComponent(
                    color = Color.Black,
                    lineCount = 3
                )
            ),
        ),
        modelProducer,
    )
}
package dev.georgiys.changerrgb.android.itemchip.telemetry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.common.Fill
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.common.data.ExtraStore
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
            if (state.typeDevice == "Temperature"){
                val list = state.points.map { Json.decodeFromJsonElement<TemperatureItemList>(it) }
                val map: Map<String, Double> = list.associate {
                    it.time to it.temperature
                }
                columnSeries { series(map.values) }
                extras { it[BottomAxisLabelKey] = map.keys.toList() }
            }
            else if (state.typeDevice == "Humidity"){
                val list = state.points.map { Json.decodeFromJsonElement<TemperatureItemList>(it) }
                val map: Map<String, Double> = list.associate {
                    it.time to it.temperature
                }
                columnSeries { series(map.values) }
                extras { it[BottomAxisLabelKey] = map.keys.toList() }
            }
            else if (state.typeDevice == "CO2ppm"){
                val list = state.points.map { Json.decodeFromJsonElement<TemperatureItemList>(it) }
                val map: Map<String, Double> = list.associate {
                    it.time to it.temperature
                }
                columnSeries { series(map.values) }
                extras { it[BottomAxisLabelKey] = map.keys.toList() }
            }
        }
    }

    CartesianChartHost(
        rememberCartesianChart(
            rememberColumnCartesianLayer(
                ColumnCartesianLayer.ColumnProvider.series(
                    rememberLineComponent(fill = Fill(color = 0xffff5500.toInt()), thickness = 100.dp)
                )
            ),
            startAxis = VerticalAxis.rememberStart(),
            bottomAxis =
                HorizontalAxis.rememberBottom(
                    itemPlacer = remember { HorizontalAxis.ItemPlacer.segmented() },
                    valueFormatter = BottomAxisValueFormatter,
                ),
        ),
        modelProducer,
    )
}
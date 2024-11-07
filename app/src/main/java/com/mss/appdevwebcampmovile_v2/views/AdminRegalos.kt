package com.mss.appdevwebcampmovile_v2.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mss.appdevwebcampmovile_v2.models.Dashboard
import com.mss.appdevwebcampmovile_v2.models.Regalo
import com.mss.appdevwebcampmovile_v2.repository.AuthRepository
import kotlinx.coroutines.launch

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.mss.appdevwebcampmovile_v2.viewModels.AdminViewModel
import com.mss.appdevwebcampmovile_v2.viewModels.AdminViewModelFactory

@Composable
fun AdminRegalos(context: Context) {
    val adminViewModel: AdminViewModel = viewModel(factory = AdminViewModelFactory(context))
    val regaloData by adminViewModel.regaloData
    val errorMessage by adminViewModel.errorMessage

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Regalos", style = MaterialTheme.typography.titleLarge, color = Color.Black, modifier = Modifier.padding(10.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        } else if (regaloData.isNotEmpty()) {
            BarChartView(regaloData)
        }
    }
}

@Composable
fun BarChartView(regaloData: List<Regalo>) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                setDrawValueAboveBar(true)
                setPinchZoom(false)
                setScaleEnabled(false)
                axisLeft.apply {
                    axisMinimum = 0f
                }
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    granularity = 1f
                    isGranularityEnabled = true
                }

                // Enable and style the legend
                legend.isEnabled = true
                legend.textColor = Color.Black.toArgb() // Set legend text color

                axisRight.isEnabled = false
            }
        },
        update = { barChart ->
            val entries = regaloData.mapIndexed { index, regalo ->
                BarEntry(index.toFloat(), regalo.total.toFloat())
            }

            // Create a BarDataSet and set colors explicitly
            val colors = listOf(
                Color(0xFFea580c).toArgb(),
                Color(0xFF84cc16).toArgb(),
                Color(0xFF22d3ee).toArgb(),
                Color(0xFFa855f7).toArgb(),
                Color(0xFFef4444).toArgb(),
                Color(0xFF14b8a6).toArgb(),
                Color(0xFFdb2777).toArgb(),
                Color(0xFFe11d48).toArgb(),
                Color(0xFF7e22ce).toArgb()
            )

            val dataSet = BarDataSet(entries, "Regalos").apply {
                setColors(colors) // Now passing a List<Int>
            }

            val data = BarData(dataSet)
            data.setValueTextColor(Color.Black.toArgb()) // Set value text color
            barChart.data = data
            barChart.invalidate()

            // Set a listener to show regalo.nombre when a bar is clicked
            barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    e?.let {
                        val index = it.x.toInt()
                        if (index in regaloData.indices) {
                            val regaloNombre = regaloData[index].nombre
                            Toast.makeText(barChart.context, "Regalo: $regaloNombre", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onNothingSelected() {
                    // No action needed
                }
            })
        }
    )
}

// Extension function to convert a Compose Color to an MPAndroidChart-compatible color
fun androidx.compose.ui.graphics.Color.toArgb(): Int = android.graphics.Color.argb(
    (alpha * 255).toInt(),
    (red * 255).toInt(),
    (green * 255).toInt(),
    (blue * 255).toInt()
)
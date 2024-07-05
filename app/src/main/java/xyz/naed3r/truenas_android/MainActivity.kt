package xyz.naed3r.truenas_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import xyz.naed3r.truenas_android.ui.theme.TrueNASAndroidTheme
import xyz.naed3r.truenas_android.api.*

val name = mutableStateOf("text")
val dashboard = Dashboard()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrueNASAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashBoard()
                }
            }
        }
    }
}

// test api key layout: Authorization: Bearer 1-ZB8dinKxQmVOxMdQ048MbDa5QoUEtbbRPEERumyjHEej9GjqmTgoFI0r2H22XFwA

@Composable
fun DashBoard() {
//    val res by name.
    val res by dashboard.DashboardViewData.collectAsState("")

    Text(
        text = "${res}!",
//        modifier = modifier
    )
    TextButton(
        onClick = {
            Log.d("tag", "Clicked")
//            observe(dashboard.getPing())
            dashboard.getPing()
//            name.value = dashboard.DashboardViewData.value
        }
    ) {
        Text(text = "Ping")
    }

}

@Preview(showBackground = true)
@Composable
fun DashBoardPreview() {
    TrueNASAndroidTheme {
        DashBoard()
    }
}

package xyz.naed3r.truenas_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lagradost.nicehttp.Requests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.naed3r.truenas_android.ui.theme.TrueNASAndroidTheme
import xyz.naed3r.truenas_android.api.*
import kotlin.concurrent.thread

val pingState = mutableStateOf<String>("");

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
                    Greeting(pingState.value)
                }
            }
        }
    }
}

// test api key layout: Authorization: Bearer 1-ZB8dinKxQmVOxMdQ048MbDa5QoUEtbbRPEERumyjHEej9GjqmTgoFI0r2H22XFwA

suspend fun getPing() {
    Log.d("res", "Starting request")
    return withContext(Dispatchers.IO) {
        val client = Requests()
//    val pong = client.get("https://10.69.1.103" + baseEndpoint + coreEndpoints[0].endpoint, mapOf("Authorization" to "Bearer: 1-ZB8dinKxQmVOxMdQ048MbDa5QoUEtbbRPEERumyjHEej9GjqmTgoFI0r2H22XFwA"))
        val pong = client.get("https://jsonplaceholder.typicode.com/todos/1")
        Log.d("res", pong.text)
        pingState.value = pong.text
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    TextButton(
        onClick = {
            Log.d("tag", "Clicked")
            suspend { getPing() }
        }
    ) {
        Text(text = "Ping")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrueNASAndroidTheme {
        Greeting("Android")
    }
}

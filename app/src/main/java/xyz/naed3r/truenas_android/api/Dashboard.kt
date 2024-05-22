package xyz.naed3r.truenas_android.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lagradost.nicehttp.Requests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.naed3r.truenas_android.api.Coroutines.ioWork

class Dashboard : ViewModel() {
    private val _DashboardViewData: MutableLiveData<String> = MutableLiveData();
    val DashboardViewData: LiveData<String> get() = _DashboardViewData;


    fun getPing() = viewModelScope.launch {
        ioWork {
            Log.d("res", "Starting request")
            val client = Requests()
//            val pong = client.get("https://10.69.1.103" + baseEndpoint + coreEndpoints[0].endpoint, mapOf("Authorization" to "Bearer: 1-ZB8dinKxQmVOxMdQ048MbDa5QoUEtbbRPEERumyjHEej9GjqmTgoFI0r2H22XFwA"))
            val pong = client.get("https://timeapi.io/api/Time/current/zone?timeZone=America/New_York")
            Log.d("res", pong.text)
            _DashboardViewData.postValue(pong.text);
        }
    }


}
package xyz.naed3r.truenas_android.api

import kotlinx.coroutines.*

object Coroutines {
    suspend fun <T, V> V.ioWork(work: suspend (CoroutineScope.(V) -> T)): T {
        val value = this
        return withContext(Dispatchers.IO) {
            work(value)
        }
    }
    suspend fun <T, V> V.mainWork(work: suspend (CoroutineScope.(V) -> T)): T {
        val value = this
        return withContext(Dispatchers.Main) {
            work(value)
        }
    }
}
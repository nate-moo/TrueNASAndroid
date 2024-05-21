package xyz.naed3r.truenas_android.api

data class Endpoint(
    val name: String,
    val endpoint: String
)

public val baseEndpoint = "/api/v2.0"

public val coreEndpoints: Array<Endpoint> = arrayOf(
    Endpoint("ping", "/core/ping"),
    Endpoint("get_jobs", "/core/get_jobs")
)

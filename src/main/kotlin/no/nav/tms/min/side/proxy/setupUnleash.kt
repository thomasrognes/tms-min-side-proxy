package no.nav.tms.min.side.proxy

import io.getunleash.DefaultUnleash
import io.getunleash.util.UnleashConfig

fun setupUnleash(unleashApiUrl: String, unleashApiKey: String, unleashEnvironment: String): DefaultUnleash {
    val appName = "tms-min-side-proxy"
    val config = UnleashConfig.builder()
        .appName(appName)
        .environment(unleashEnvironment)
        .instanceId(appName)
        .unleashAPI("$unleashApiUrl/api")
        .apiKey(unleashApiKey)
        .build()
    return DefaultUnleash(config)
}
package no.nav.tms.min.side.proxy

import io.getunleash.DefaultUnleash
import io.getunleash.util.UnleashConfig
import io.ktor.server.application.ApplicationEnvironment

fun setupUnleash(environment: ApplicationEnvironment): DefaultUnleash {
    val appName = "tms-min-side-proxy"
    val unleashApiUrl = "${environment.config.property("unleash.unleash_server_api_url").getString()}/api"
    val unleashApiKey = environment.config.property("unleash.unleash_server_api_token").getString()
    val unleashEnvironment = environment.config.property("unleash.environment").getString()
    val config = UnleashConfig.builder()
        .appName(appName)
        .environment(unleashEnvironment)
        .instanceId(appName)
        .unleashAPI(unleashApiUrl)
        .apiKey(unleashApiKey)
        .build()
    return DefaultUnleash(config)
}
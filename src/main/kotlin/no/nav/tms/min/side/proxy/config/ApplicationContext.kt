package no.nav.tms.min.side.proxy.config

import no.nav.tms.min.side.proxy.health.HealthService

class ApplicationContext {

    val httpClient = HttpClientBuilder.build()
    val healthService = HealthService(this)

}

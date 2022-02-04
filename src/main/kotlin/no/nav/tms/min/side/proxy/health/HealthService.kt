package no.nav.tms.min.side.proxy.health

import no.nav.tms.min.side.proxy.config.ApplicationContext

class HealthService(private val applicationContext: ApplicationContext) {

    suspend fun getHealthChecks(): List<HealthStatus> {
        return emptyList()
    }
}

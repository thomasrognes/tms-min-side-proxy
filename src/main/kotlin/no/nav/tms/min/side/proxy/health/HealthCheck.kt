package no.nav.tms.min.side.proxy.health

interface HealthCheck {

    suspend fun status(): HealthStatus

}

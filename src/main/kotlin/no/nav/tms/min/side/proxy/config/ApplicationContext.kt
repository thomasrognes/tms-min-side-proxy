package no.nav.tms.min.side.proxy.config

import no.nav.tms.min.side.proxy.arbeid.ArbeidConsumer
import no.nav.tms.min.side.proxy.common.TokenFetcher
import no.nav.tms.min.side.proxy.dittnav.DittnavConsumer
import no.nav.tms.min.side.proxy.health.HealthService
import no.nav.tms.min.side.proxy.sykefravaer.SykefravaerConsumer
import no.nav.tms.min.side.proxy.utkast.UtkastConsumer
import no.nav.tms.token.support.tokendings.exchange.TokendingsServiceBuilder

class ApplicationContext {
    private val environment = Environment()

    val httpClient = HttpClientBuilder.build()
    val healthService = HealthService(this)

    val tokendingsService = TokendingsServiceBuilder.buildTokendingsService()
    val tokenFetcher = TokenFetcher(
        tokendingsService,
        environment.arbeidApiClientId,
        environment.dittnavApiClientId,
        environment.sykefravaerApiClientId,
        environment.utkastClientId
    )

    val arbeidConsumer = ArbeidConsumer(httpClient, tokenFetcher, environment.arbeidApiBaseUrl)
    val dittnavConsumer = DittnavConsumer(httpClient, tokenFetcher, environment.dittnavApiBaseUrl)
    val sykefravaerConsumer = SykefravaerConsumer(httpClient, tokenFetcher, environment.sykefravaerApiBaseUrl)
    val utkastConsumer = UtkastConsumer(httpClient,tokenFetcher, environment.utastBaseUrl)

}

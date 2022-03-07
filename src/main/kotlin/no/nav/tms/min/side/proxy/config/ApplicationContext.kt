package no.nav.tms.min.side.proxy.config

import no.nav.tms.min.side.proxy.arbeid.ArbeidConsumer
import no.nav.tms.min.side.proxy.dittnav.DittnavConsumer
import no.nav.tms.min.side.proxy.health.HealthService
import no.nav.tms.min.side.proxy.sykefravaer.SykefravaerConsumer
import no.nav.tms.token.support.tokendings.exchange.TokendingsServiceBuilder
import java.net.URL

class ApplicationContext {
    private val environment = Environment()

    val httpClient = HttpClientBuilder.build()
    val healthService = HealthService(this)

    val tokendingsService = TokendingsServiceBuilder.buildTokendingsService()
    val tokenFetcher = TokenFetcher(tokendingsService, environment.dittnavClientId, environment.arbeidClientId, environment.sykdomClientId)

    val arbeidConsumer = ArbeidConsumer(httpClient, tokenFetcher, URL("https://endpoint.no"))
    val dittnavConsumer = DittnavConsumer(httpClient, tokenFetcher, URL("https://endpoint.no"))
    val sykefravaerConsumer = SykefravaerConsumer(httpClient, tokenFetcher, URL("https://endpoint.no"))

}

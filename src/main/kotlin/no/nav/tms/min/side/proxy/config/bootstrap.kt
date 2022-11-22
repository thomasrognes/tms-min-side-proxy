package no.nav.tms.min.side.proxy.config

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.util.*
import no.nav.tms.min.side.proxy.arbeid.arbeidApi
import no.nav.tms.min.side.proxy.dittnav.dittnavApi
import no.nav.tms.min.side.proxy.health.healthApi
import no.nav.tms.min.side.proxy.sykefravaer.sykefraverApi
import no.nav.tms.min.side.proxy.utkast.utkastApi
import no.nav.tms.token.support.idporten.sidecar.LoginLevel
import no.nav.tms.token.support.idporten.sidecar.installIdPortenAuth

@KtorExperimentalAPI
fun Application.mainModule(appContext: ApplicationContext = ApplicationContext()) {
    val environment = Environment()

    install(DefaultHeaders)

    install(CORS) {
        host(environment.corsAllowedOrigins, schemes = listOf(environment.corsAllowedSchemes))
        allowCredentials = true
        header(HttpHeaders.ContentType)
        method(HttpMethod.Options)
    }

    install(ContentNegotiation) {
        json(jsonConfig())
    }

    installIdPortenAuth {
        setAsDefault = true
        loginLevel = LoginLevel.LEVEL_3
    }

    routing {
        healthApi(appContext.healthService)

        authenticate {
            arbeidApi(appContext.arbeidConsumer)
            dittnavApi(appContext.dittnavConsumer)
            sykefraverApi(appContext.sykefravaerConsumer)
            utkastApi(appContext.utkastConsumer)
        }
    }

    configureShutdownHook(appContext.httpClient)
}

private fun Application.configureShutdownHook(httpClient: HttpClient) {
    environment.monitor.subscribe(ApplicationStopping) {
        httpClient.close()
    }
}

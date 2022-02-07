package no.nav.tms.min.side.proxy.config

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.util.*
import no.nav.tms.min.side.proxy.health.healthApi
import no.nav.tms.token.support.idporten.sidecar.LoginLevel
import no.nav.tms.token.support.idporten.sidecar.installIdPortenAuth

@KtorExperimentalAPI
fun Application.mainModule(appContext: ApplicationContext = ApplicationContext()) {
    val environment = Environment()

    install(DefaultHeaders)

    install(CORS) {
        host(environment.corsAllowedOrigins)
        allowCredentials = true
        header(HttpHeaders.ContentType)
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
            get("/sikret") {
                call.respond(HttpStatusCode.OK)
            }
        }
    }

    configureShutdownHook(appContext.httpClient)
}

private fun Application.configureShutdownHook(httpClient: HttpClient) {
    environment.monitor.subscribe(ApplicationStopping) {
        httpClient.close()
    }
}

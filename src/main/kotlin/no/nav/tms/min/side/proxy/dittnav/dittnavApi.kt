package no.nav.tms.min.side.proxy.dittnav

import no.nav.tms.min.side.proxy.arbeid.ArbeidConsumer

import io.ktor.application.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import no.nav.tms.token.support.idporten.sidecar.user.IdportenUser
import no.nav.tms.token.support.idporten.sidecar.user.IdportenUserFactory
import org.slf4j.LoggerFactory

fun Route.dittnavApi(consumer: DittnavConsumer) {

    val log = LoggerFactory.getLogger(DittnavConsumer::class.java)

    get("/dittnav/*") {
        val endpoint = "dittnav/endpoint"

        try {
            val response = consumer.getContent(authenticatedUser)
            call.respond(response.status, response.readBytes())
        } catch (exception: Exception) {
            log.warn("Klarte ikke hente data fra endepunktet '$endpoint'. Feilmelding: [${exception.message}]. $authenticatedUser", exception)
            call.respond(HttpStatusCode.ServiceUnavailable)
        }
    }

}

private val PipelineContext<Unit, ApplicationCall>.authenticatedUser: IdportenUser
    get() = IdportenUserFactory.createIdportenUser(call)



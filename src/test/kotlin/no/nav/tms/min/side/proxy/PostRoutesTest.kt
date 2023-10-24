package no.nav.tms.min.side.proxy

import io.kotest.matchers.shouldBe
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication
import io.mockk.mockk
import no.nav.tms.min.side.proxy.TestParameters.Companion.getParameters
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PostRoutesTest {


    @Test
    fun `post statistikk`() = testApplication {
        val applicationhttpClient = testApplicationHttpClient()
        var callCount = 0
        val proxyHttpClient = ProxyHttpClient(applicationhttpClient, tokendigsMock, azureMock)
        mockApi(
            contentFetcher = contentFecther(proxyHttpClient),
            externalContentFetcher = externalContentFetcher(proxyHttpClient),
            navnFetcher = mockk()
        )

        externalServices {
            hosts("http://statistikk.test") {
                routing {
                    post("/innlogging") {
                        callCount += 1
                        call.receive<StatistikkPostRequest>().ident shouldBe "12345"
                        call.respond(HttpStatusCode.OK)
                    }
                }
            }
        }

        client.authenticatedPost("/statistikk/innlogging").assert {
            status shouldBe HttpStatusCode.OK
        }
        callCount shouldBe 1
    }

    private fun externalContentFetcher(proxyHttpClient: ProxyHttpClient) = ExternalContentFetcher(
        proxyHttpClient = proxyHttpClient,
        aapClientId = "aap",
        aapBaseUrl = "",
        meldekortClientId = "meldekort",
        meldekortBaseUrl = "",
        aiaBaseUrl = "placeholder",
        aiaClientId = "aia"
    )

    private fun contentFecther(proxyHttpClient: ProxyHttpClient) = ContentFetcher(
        proxyHttpClient = proxyHttpClient,
        utkastClientId = "",
        utkastBaseUrl = "",
        selectorClientId = "",
        selectorBaseUrl = "",
        statistikkClientId = "statistikk",
        statistikkBaseApiUrl = "http://statistikk.test",
        oppfolgingClientId = "",
        oppfolgingBaseUrl = ""
    )
}

private class StatistikkPostRequest(val ident: String)

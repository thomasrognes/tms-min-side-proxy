package no.nav.tms.min.side.proxy.arbeid

import io.ktor.client.*
import io.ktor.client.statement.*
import no.nav.tms.min.side.proxy.config.AccessToken
import no.nav.tms.min.side.proxy.config.TokenFetcher
import no.nav.tms.min.side.proxy.config.get
import no.nav.tms.token.support.idporten.sidecar.user.IdportenUser
import java.net.URL

class ArbeidConsumer(
    private val httpClient: HttpClient,
    private val tokenFetcher: TokenFetcher,
    private val url: URL,
) {

    suspend fun getContent(user: IdportenUser): HttpResponse {
        val accessToken = AccessToken(tokenFetcher.getArbeidToken(user.tokenString))

        return httpClient.get(url, accessToken)
    }
}

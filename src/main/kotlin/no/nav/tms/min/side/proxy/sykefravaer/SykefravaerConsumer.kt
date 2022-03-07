package no.nav.tms.min.side.proxy.sykefravaer

import io.ktor.client.*
import io.ktor.client.statement.*
import no.nav.tms.min.side.proxy.common.AccessToken
import no.nav.tms.min.side.proxy.common.TokenFetcher
import no.nav.tms.min.side.proxy.config.get
import no.nav.tms.token.support.idporten.sidecar.user.IdportenUser
import java.net.URL

class SykefravaerConsumer(
    private val httpClient: HttpClient,
    private val tokenFetcher: TokenFetcher,
    private val baseUrl: URL,
) {

    suspend fun getContent(user: IdportenUser, proxyPath: String?): HttpResponse {
        val accessToken = AccessToken(tokenFetcher.getSykefravaerToken(user.tokenString))
        val url = "$baseUrl/$proxyPath"

        return httpClient.get(url, accessToken)
    }
}


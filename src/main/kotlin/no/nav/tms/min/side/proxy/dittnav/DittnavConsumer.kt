package no.nav.tms.min.side.proxy.dittnav

import io.ktor.client.*
import io.ktor.client.statement.*
import no.nav.tms.min.side.proxy.common.AccessToken
import no.nav.tms.min.side.proxy.common.TokenFetcher
import no.nav.tms.min.side.proxy.config.get
import no.nav.tms.min.side.proxy.config.post
import no.nav.tms.token.support.idporten.sidecar.user.IdportenUser

class DittnavConsumer(
    private val httpClient: HttpClient,
    private val tokenFetcher: TokenFetcher,
    private val baseUrl: String,
) {

    suspend fun getContent(user: IdportenUser,  proxyPath: String?): HttpResponse {
        val accessToken = AccessToken(tokenFetcher.getDittnavApiToken(user.tokenString))
        val url = "$baseUrl/$proxyPath"

        return httpClient.get(url, accessToken)
    }

    suspend fun postContent(user: IdportenUser, content: String, proxyPath: String?): HttpResponse {
        val accessToken = AccessToken(tokenFetcher.getDittnavApiToken(user.tokenString))
        val url = "$baseUrl/$proxyPath"

        return httpClient.post(url, content, accessToken)
    }

}

package no.nav.tms.min.side.proxy.config

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import no.nav.tms.min.side.proxy.common.AccessToken

suspend inline fun <reified T> HttpClient.get(url: String, accessToken: AccessToken): T = withContext(Dispatchers.IO) {
    request {
        url(url)
        method = HttpMethod.Get
        header(HttpHeaders.Authorization, "Bearer ${accessToken.value}")
    }
}

suspend inline fun <reified T> HttpClient.post(url: String, content: String, accessToken: AccessToken): T = withContext(Dispatchers.IO) {
    request {
        url(url)
        method = HttpMethod.Post
        header(HttpHeaders.Authorization, "Bearer ${accessToken.value}")
        contentType(ContentType.Application.Json)
        body = content
    }
}



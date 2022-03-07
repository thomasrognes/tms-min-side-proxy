package no.nav.tms.min.side.proxy.config

import no.nav.tms.token.support.tokendings.exchange.TokendingsService

class TokenFetcher(
    private val tokendingsService: TokendingsService,
    private val minSideClientId: String,
    private val arbeidClientId: String,
    private val sykefravaerClientId: String,
) {

    suspend fun getDittnavToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, minSideClientId)
    }

    suspend fun getArbeidToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, arbeidClientId)
    }

    suspend fun getSykefravaerToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, sykefravaerClientId)
    }
}

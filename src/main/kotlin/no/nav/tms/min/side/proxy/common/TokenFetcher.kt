package no.nav.tms.min.side.proxy.common

import no.nav.tms.token.support.tokendings.exchange.TokendingsService

class TokenFetcher(
    private val tokendingsService: TokendingsService,
    private val arbeidClientId: String,
    private val dittnavClientId: String,
    private val sykefravaerClientId: String,
) {

    suspend fun getArbeidToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, arbeidClientId)
    }

    suspend fun getDittnavToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, dittnavClientId)
    }

    suspend fun getSykefravaerToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, sykefravaerClientId)
    }
}

package no.nav.tms.min.side.proxy.common

import no.nav.tms.token.support.tokendings.exchange.TokendingsService

class TokenFetcher(
    private val tokendingsService: TokendingsService,
    private val arbeidClientId: String,
    private val dittnavClientId: String,
    private val sykefravaerClientId: String,
    private val ukastClientId: String
) {

    suspend fun getArbeidApiToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, arbeidClientId)
    }

    suspend fun getDittnavApiToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, dittnavClientId)
    }

    suspend fun getSykefravaerApiToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, sykefravaerClientId)
    }
    suspend fun getUtkastApiToken(userToken: String): String {
        return tokendingsService.exchangeToken(userToken, ukastClientId)
    }
}

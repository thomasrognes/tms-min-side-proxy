package no.nav.tms.min.side.proxy

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

class JwtStub(private val issuer: String = "test issuer") {

    private val privateKey: RSAPrivateKey
    private val publicKey: RSAPublicKey

    init {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(512)

        val keyPair = keyPairGenerator.genKeyPair()
        privateKey = keyPair.private as RSAPrivateKey
        publicKey = keyPair.public as RSAPublicKey
    }

    fun createTokenFor(pid: String, audience: String = "", authLevel: String = "Level4"): String {
        val algorithm = Algorithm.RSA256(publicKey, privateKey)

        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("pid", pid)
            .withClaim("acr",authLevel)
            .sign(algorithm)
    }
}

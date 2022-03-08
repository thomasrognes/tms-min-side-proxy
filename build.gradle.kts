import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm").version(Kotlin.version)
    kotlin("plugin.allopen").version(Kotlin.version)

    id(Shadow.pluginId) version (Shadow.version)
    // Apply the application plugin to add support for building a CLI application.
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    maven("https://packages.confluent.io/maven")
    maven("https://jitpack.io")
    mavenLocal()
}

dependencies {
    implementation(DittNAV.Common.logging)
    implementation(DittNAV.Common.utils)
    implementation(Jackson.dataTypeJsr310)
    implementation(Kotlinx.coroutines)
    implementation(Kotlinx.htmlJvm)
    implementation(Ktor.auth)
    implementation(Ktor.authJwt)
    implementation(Ktor.clientApache)
    implementation(Ktor.clientJackson)
    implementation(Ktor.clientJson)
    implementation(Ktor.clientLogging)
    implementation(Ktor.clientLoggingJvm)
    implementation(Ktor.clientSerializationJvm)
    implementation(Ktor.htmlBuilder)
    implementation(Ktor.jackson)
    implementation(Ktor.serverNetty)
    implementation(Ktor.serialization)
    implementation(Logback.classic)
    implementation(Logstash.logbackEncoder)
    implementation(Tms.KtorTokenSupport.tokendingsExchange)
    implementation(Tms.KtorTokenSupport.idportenSidecar)

    testImplementation(Junit.api)
    testImplementation(Ktor.clientMock)
    testImplementation(Ktor.clientMockJvm)
    testImplementation(Kluent.kluent)
    testImplementation(Mockk.mockk)
    testImplementation(Jjwt.api)

    testRuntimeOnly(Bouncycastle.bcprovJdk15on)
    testRuntimeOnly(Jjwt.impl)
    testRuntimeOnly(Jjwt.jackson)
    testRuntimeOnly(Junit.engine)
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events("passed", "skipped", "failed")
        }
    }

    register("runServer", JavaExec::class) {

        environment("CORS_ALLOWED_ORIGINS", "localhost:9002")
        environment("ARBEID_API_CLIENT_ID", "arbeidId")
        environment("ARBEID_API_URL", "https://arbeid.no")
        environment("DITTNAV_API_CLIENT_ID", "dittnavId")
        environment("DITTNAV_API_URL", "https://dittnav.no")
        environment("SYKEFRAVAER_API_CLIENT_ID", "sykefravaerId")
        environment("SYKEFRAVAER_API_URL", "https://sykefravaer.no")

        environment("NAIS_CLUSTER_NAME", "dev-sbs")
        environment("NAIS_NAMESPACE", "personbruker")

        environment("IDPORTEN_WELL_KNOWN_URL", "https://oidc-ver2.difi.no/idporten-oidc-provider/.well-known/openid-configuration")
        environment("IDPORTEN_CLIENT_ID", "e89006c5-7193-4ca3-8e26-d0990d9d981f")

        environment("TOKEN_X_WELL_KNOWN_URL", "https://tokendings.dev-gcp.nais.io/.well-known/oauth-authorization-server")
        environment("TOKEN_X_CLIENT_ID", "dummy-tokenX-client-id")
        val dummyJwks = """{"p":"-xwJW4TQT_KrMVnWR39Mpxf1WSnSaI67E2YXnCdtNoz0l_xc7nf8skc7vD-FbwOm-sEmdQk4jLKWCoTS8IHV0qUTD_Ppix4j88d2uBMMpPyfMXPmvYxUCw-nF4kI50DqweLfwtcpuyr1byMXAHvxuTuWrYnyTJ3AVDOGIaTnRB0","kty":"RSA","q":"ll0WRWtKFB0Hul9DG7ptHZ_VyaIGrPZ1-XEbaHrrbJVECsD5OfulhuINnGAt8OGWNuEmqcBOntC1rdZjOs0-3-yuGsHPbuRzM-mVM1weoUTV98KQjB_sdiE3Ih-CsKwyDmBpgrASApNN-r6LsisNiwqNgRqThsE5nHJTJvSbjJ0","d":"VuV5r7QkDt3Zoiyp5wenXcY3ptNFQdLYCKn-lfYm9TQ4o5ZvjpeXOxE0TEX-TFT67Vs0MpDgsVl7TbmTlqjSvJWxwpy_sSANWlIPImlQWaPsqTSAROQf_NpmZpk_4cvmCg0SM6BWv92g7TKtyX2yV5JdtSCOQU1wV0JD-CZ_bhT7CwwQ9ObEC--JM9ptL1Jy4ZQip7QkvNrfh-TjeIn9f9TW7kETh8GmlmovmYJFHXTiCOpdes1Io3IFYqIFoGtbfTXV1lCavfnHktHYnOrK9Tj5JNAf4Rp_aEymi-Y7sFccM9anWNHukVSc9rrtPIFEt5blAxlNeuYdqwAx0GuBQQ","e":"AQAB","use":"sig","kid":"KID","qi":"muEsRyWwBSl0CPBxmZsLXz7NpSsIgf9gWXMmfeMFGPQvcViL-3Wrv9IPveJy5ihJSzsZGOzPmiaquewntUVXAvm5dOLiIal4MZUtZPAhrhCRxCRspYqLp4u7Fb2aMhMo2CoCBFC5T4MfU250u_tAOZhlrfPzNch_igLLQqoRrGQ","dp":"HlSonLFSKBX7r55WT5SEwboXHIn8rDxxREqUl3v7qRclhCYrY3KCx1XrVTWm_F3IkYk7B-_xMK1xihu5Duvf0-20e7zOfMtLNGrnYByM7nDFGcgSGtsUW7GsUR9wP96LfJfWx0YN-FmcA6yNXrWZ4PHdpWCAL9juHj2K-g1dEdE","alg":"RS256","dq":"JdOfMbGO_kZbVlh2wngA0U4Pc10ufr616R26PmuF5FgcuPPY_uw-tRMTR36usAWgS4gSuOunG673tZbUect-gMjC9_o_2-7eyHV_0l7fWcS-a0joIkg5rXIns47nythW82Tvxi_TKBC0slrTO-w2yP7LoGn2KRVdD-1227r3ksU","n":"k328g4arE1iN78Ig9m--5vmymCO5K_HZul8LKiwOZW88-ALikb_ponB-pN12Dpudrasy0xTyMp10f4qu4EjVeImTey07eIho-57JUX_s7M0Yq9vjoe9uQE0JULLlQuHzky53FJ-CMHR7canGo0giTJGUAZRnOqkoNZaTrkfrjodYB8vuRwIT_PJCOmIIkHiR2i8KDUP2rxCDKnL7Ed-jiyaeyDW8TsB4z3Dmt6Jke03KzOU6061UMySHWeh-OjdHgseGf1iAwAcv1lfsRlLr3_E2PvzE0IVayBZBPy98Y4R8G2svg1EcyXMPJrhWkfuxzu4jTiKXOXKO0nEQfKOhyQ"}"""
        environment("TOKEN_X_PRIVATE_JWK", dummyJwks)

        main = application.mainClassName
        classpath = sourceSets["main"].runtimeClasspath
    }
}

apply(plugin = Shadow.pluginId)

package no.nav.tms.min.side.proxy.config

import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(
    val corsAllowedOrigins: String = getEnvVar("CORS_ALLOWED_ORIGINS"),
    val minSideClientId: String = getEnvVar("MIN_SIDE_CLIENT_ID"),
    val arbeidClientId: String = getEnvVar("ARBEID_CLIENT_ID"),
    val sykdomClientId: String = getEnvVar("SYKEFRAVAER_CLIENT_ID"),

    // InfluxDB
    /*
    val influxdbHost: String = getEnvVar("INFLUXDB_HOST"),
    val influxdbPort: Int = IntEnvVar.getEnvVarAsInt("INFLUXDB_PORT"),
    val influxdbName: String = getEnvVar("INFLUXDB_DATABASE_NAME"),
    val influxdbUser: String = getEnvVar("INFLUXDB_USER"),
    val influxdbPassword: String = getEnvVar("INFLUXDB_PASSWORD"),
    val influxdbRetentionPolicy: String = getEnvVar("INFLUXDB_RETENTION_POLICY"),
    */
)

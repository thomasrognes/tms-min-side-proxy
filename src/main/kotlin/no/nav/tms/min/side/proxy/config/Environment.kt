package no.nav.tms.min.side.proxy.config

import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(
    val corsAllowedOrigins: String = getEnvVar("CORS_ALLOWED_ORIGINS"),
    val arbeidBaseUrl: String = getEnvVar("ARBEID_URL"),
    val arbeidClientId: String = getEnvVar("ARBEID_CLIENT_ID"),
    val dittnavClientId: String = getEnvVar("DITTNAV_CLIENT_ID"),
    val dittnavBaseUrl: String = getEnvVar("DITTNAV_URL"),
    val sykefravaerClientId: String = getEnvVar("SYKEFRAVAER_CLIENT_ID"),
    val sykefravaerBaseUrl: String = getEnvVar("SYKEFRAVAER_URL"),

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

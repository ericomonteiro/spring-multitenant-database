package ericomonteiro.github.com.multitenant.config.datasource

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "configuration.tenant")
data class DataSourceConfig(
    var default: String,
    var datasource: Map<String, DataSource>
) {
    data class DataSource(
        var url: String,
        var username: String,
        var password: String,
        var driver: String
    )
}
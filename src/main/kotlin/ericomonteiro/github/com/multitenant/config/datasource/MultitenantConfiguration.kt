package ericomonteiro.github.com.multitenant.config.datasource

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Paths
import java.util.Properties
import javax.sql.DataSource


@Configuration
class MultitenantConfiguration(
    private val dataSourceConfig: DataSourceConfig
) {
    @Bean
    fun dataSource(): DataSource {
        val resolvedDataSources: MutableMap<Any?, Any> = HashMap()
        dataSourceConfig.datasource.forEach { tenant ->
            val tenantProperties = tenant.value
            val dataSourceBuilder = DataSourceBuilder.create()
            val tenantId: String = tenant.key

            dataSourceBuilder.driverClassName(tenantProperties.driver)
            dataSourceBuilder.username(tenantProperties.username)
            dataSourceBuilder.password(tenantProperties.password)
            dataSourceBuilder.url(tenantProperties.url)
            resolvedDataSources[tenantId] = dataSourceBuilder.build()
        }

        val dataSource: AbstractRoutingDataSource = MultitenantDataSource()
        dataSource.setDefaultTargetDataSource(resolvedDataSources[dataSourceConfig.default]!!)
        dataSource.setTargetDataSources(resolvedDataSources)
        dataSource.afterPropertiesSet()
        return dataSource
    }
}
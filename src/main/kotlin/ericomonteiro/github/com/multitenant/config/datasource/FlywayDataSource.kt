package ericomonteiro.github.com.multitenant.config.datasource

import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class FlywayDataSource(
    private val dataSource: DataSource
) {

    @PostConstruct
    fun migrateFlyway() {
        dataSource as MultitenantDataSource
        dataSource.resolvedDataSources.forEach {
            println(it)
            Flyway.configure()
                .dataSource( it.value)
                .locations("db/migration")
                .load()
                .migrate()
        }
    }

}
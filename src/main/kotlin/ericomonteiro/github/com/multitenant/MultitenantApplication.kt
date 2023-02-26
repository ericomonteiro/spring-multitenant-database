package ericomonteiro.github.com.multitenant

import ericomonteiro.github.com.multitenant.config.datasource.DataSourceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties(DataSourceConfig::class)
class MultitenantApplication

fun main(args: Array<String>) {
	runApplication<MultitenantApplication>(*args)
}

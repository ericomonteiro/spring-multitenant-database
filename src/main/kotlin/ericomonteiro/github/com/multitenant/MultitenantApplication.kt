package ericomonteiro.github.com.multitenant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class MultitenantApplication

fun main(args: Array<String>) {
	runApplication<MultitenantApplication>(*args)
}

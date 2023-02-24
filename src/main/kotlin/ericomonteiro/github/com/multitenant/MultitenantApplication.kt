package ericomonteiro.github.com.multitenant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MultitenantApplication

fun main(args: Array<String>) {
	runApplication<MultitenantApplication>(*args)
}

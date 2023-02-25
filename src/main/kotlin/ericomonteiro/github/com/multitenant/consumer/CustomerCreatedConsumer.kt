package ericomonteiro.github.com.multitenant.consumer

import ericomonteiro.github.com.multitenant.constants.KafkaTopics
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CustomerCreatedConsumer {

    val logger: Logger = LoggerFactory.getLogger(CustomerCreatedConsumer::class.java)

    @KafkaListener(
            id = "demo-consumer",
            topics = [KafkaTopics.CUSTOMER_CREATED]
    )
    fun handle(payload: ConsumerRecord<String, String>) {
        payload
            .also {
                logger.info("value=${ it.value() }")
            }
            .also {
                logger.info("headers:")
                it.headers().forEach { header ->
                    logger.info("name=${header.key()} value=${String(header.value())}")
                }
            }
    }

}
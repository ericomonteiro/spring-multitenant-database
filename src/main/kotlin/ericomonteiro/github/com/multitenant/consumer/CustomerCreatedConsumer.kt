package ericomonteiro.github.com.multitenant.consumer

import com.google.gson.Gson
import ericomonteiro.github.com.multitenant.constants.KafkaTopics
import ericomonteiro.github.com.multitenant.constants.QueueNames
import ericomonteiro.github.com.multitenant.dto.CustomerSimple
import ericomonteiro.github.com.multitenant.model.Customer
import ericomonteiro.github.com.multitenant.service.SQSMessageService
import ericomonteiro.github.com.multitenant.util.toJson
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CustomerCreatedConsumer(
    private val sqsMessageService: SQSMessageService
) {

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

        val customer = Gson().fromJson(payload.value(), Customer::class.java)
        val customerSimple = CustomerSimple.fromModel(customer)
        sqsMessageService.sendMessage(
            queueName = QueueNames.CUSTOMER_CONFIRMATION_REQUESTED_BY_EMAIL,
            message = customerSimple.toJson()
        )
    }

}
package ericomonteiro.github.com.multitenant.service

import ericomonteiro.github.com.multitenant.config.TenantContext
import ericomonteiro.github.com.multitenant.constants.Headers
import io.awspring.cloud.sqs.operations.SendResult
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.stereotype.Service

@Service
class SQSMessageService(
    private val sqsTemplate: SqsTemplate
) {

    private val logger: Logger = LoggerFactory.getLogger(SQSMessageService::class.java)

    fun sendMessage(queueName: String, message: String) =
        sendMessage(queueName, message, emptyMap())


    fun sendMessage(queueName: String, message: String, attributes: Map<String, String>) {
        val result: SendResult<String> = sqsTemplate.send { opts ->
            opts.queue(queueName)
                .payload(message)
                .headers(
                    attributes.toMutableMap()
                        .apply { put(Headers.TENANT_ID, TenantContext.currentTenant) }
                        as Map<String, String>
                )
        }

        logger.info("messageId=${result.messageId} endpoint=${result.endpoint} message=${result.message}")
    }

}
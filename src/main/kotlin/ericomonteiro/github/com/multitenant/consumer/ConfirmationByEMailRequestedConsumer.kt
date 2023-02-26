package ericomonteiro.github.com.multitenant.consumer

import ericomonteiro.github.com.multitenant.constants.QueueNames
import io.awspring.cloud.sqs.annotation.SqsListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.sqs.model.Message

@Service
class ConfirmationByEMailRequestedConsumer {

    private val logger: Logger = LoggerFactory
            .getLogger(ConfirmationByEMailRequestedConsumer::class.java)

    @SqsListener(
            QueueNames.CUSTOMER_CONFIRMATION_REQUESTED_BY_EMAIL
    )
    fun handler(message: Message) {
        logger.info("body=${message.body()}")
        logger.info("attributes")
        message.attributes().forEach { att ->
            logger.info("att=${att.key} value=${att.value}")
        }
        logger.info("message attributes:")
        message.messageAttributes().forEach { att ->
            logger.info("att=${att.key} value=${att.value}")
        }
    }

}
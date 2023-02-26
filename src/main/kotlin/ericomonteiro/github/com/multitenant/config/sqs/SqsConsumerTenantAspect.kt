package ericomonteiro.github.com.multitenant.config.sqs

import ericomonteiro.github.com.multitenant.config.TenantContext
import ericomonteiro.github.com.multitenant.constants.Headers
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.model.Message

@Aspect
@Component
class SqsConsumerTenantAspect {

    private val logger: Logger = LoggerFactory.getLogger(SqsConsumerTenantAspect::class.java)

    @Before(
            "@annotation(io.awspring.cloud.sqs.annotation.SqsListener)"
    )
    fun beforeReceiveMessage(joinPoint: JoinPoint) {
        logger.info("before sqs message")
        joinPoint.args.forEach { arg ->
            logger.info(arg.toString())
            if (arg is Message) {
                arg.messageAttributes()[Headers.TENANT_ID]
                    ?.stringValue()
                    ?.let { tenantInMessage: String ->
                        TenantContext.currentTenant = tenantInMessage
                    }
            }
        }
    }

}
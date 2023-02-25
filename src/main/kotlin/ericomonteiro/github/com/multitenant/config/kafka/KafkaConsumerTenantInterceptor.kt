package ericomonteiro.github.com.multitenant.config.kafka

import ericomonteiro.github.com.multitenant.config.TenantContext
import ericomonteiro.github.com.multitenant.constants.Headers
import org.apache.kafka.clients.consumer.ConsumerInterceptor
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class KafkaConsumerTenantInterceptor<K, V>: ConsumerInterceptor<K, V> {

    private val logger: Logger = LoggerFactory.getLogger(KafkaConsumerTenantInterceptor::class.java)

    override fun configure(p0: MutableMap<String, *>?) {
    }

    override fun close() {
    }

    override fun onCommit(p0: MutableMap<TopicPartition, OffsetAndMetadata>?) {
    }

    override fun onConsume(records: ConsumerRecords<K, V>): ConsumerRecords<K, V> {
        records
            .single()
            ?.headers()
                ?.first { header -> header.key() == Headers.TENANT_ID }
                ?.also { tenantHeader ->
                    val tenant = String(tenantHeader.value())
                    logger.info("set tenant at consumer to tenant=$tenant")
                    TenantContext.currentTenant = tenant
                }

        return records
    }

}
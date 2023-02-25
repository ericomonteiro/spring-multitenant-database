package ericomonteiro.github.com.multitenant.config.kafka

import ericomonteiro.github.com.multitenant.config.TenantContext
import ericomonteiro.github.com.multitenant.constants.Headers.TENANT_ID
import org.apache.kafka.clients.producer.ProducerInterceptor
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception

class KafkaProducerTenantInterceptor<K, V>: ProducerInterceptor<K, V> {

    private val logger: Logger = LoggerFactory.getLogger(KafkaProducerTenantInterceptor::class.java)

    override fun onSend(record: ProducerRecord<K, V>): ProducerRecord<K, V> {
        logger.info("set tenant header in message to tenant=${TenantContext.currentTenant}")
        record.headers()
                .apply { add(TENANT_ID, TenantContext.currentTenant.toByteArray()) }

        return record
    }

    override fun configure(p0: MutableMap<String, *>?) {
    }

    override fun close() {
    }

    override fun onAcknowledgement(p0: RecordMetadata?, p1: Exception?) {
    }
}
package ericomonteiro.github.com.multitenant.config.kafka

import ericomonteiro.github.com.multitenant.constants.KafkaTopics
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class KafkaTopicsConfigurations {
    @Bean
    fun topicCustomerCreated(): NewTopic =
            TopicBuilder.name(KafkaTopics.CUSTOMER_CREATED)
                .partitions(1)
                .replicas(1)
                .build()
}
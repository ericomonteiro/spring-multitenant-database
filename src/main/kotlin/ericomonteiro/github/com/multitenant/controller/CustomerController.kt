package ericomonteiro.github.com.multitenant.controller

import ericomonteiro.github.com.multitenant.constants.KafkaTopics
import ericomonteiro.github.com.multitenant.model.Customer
import ericomonteiro.github.com.multitenant.repository.CustomerRepository
import ericomonteiro.github.com.multitenant.util.toJson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(
    private val customerRepository: CustomerRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable customerId: String) =
        ResponseEntity.ok(customerRepository.findById(customerId))

    @PostMapping
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> {
        val result = customerRepository.save(customer)
        kafkaTemplate.send(KafkaTopics.CUSTOMER_CREATED, result.toJson())
        return ResponseEntity.status(HttpStatus.CREATED).body(customer)
    }

}
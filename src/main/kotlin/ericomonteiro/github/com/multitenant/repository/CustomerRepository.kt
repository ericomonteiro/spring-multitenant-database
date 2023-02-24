package ericomonteiro.github.com.multitenant.repository

import ericomonteiro.github.com.multitenant.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, String>
package ericomonteiro.github.com.multitenant.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Customer (
    @Id @Column(length = 36)
    val id: String,

    val name: String
)
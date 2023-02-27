package ericomonteiro.github.com.multitenant.dto

import com.google.gson.Gson
import ericomonteiro.github.com.multitenant.model.Customer

data class CustomerSimple(
    val id: String,
    val name: String
) {
    companion object {
        fun fromModel(customer: Customer) = CustomerSimple(customer.id, customer.name)
        fun fromJson(json: String) = Gson().fromJson(json, CustomerSimple::class.java)

    }
}
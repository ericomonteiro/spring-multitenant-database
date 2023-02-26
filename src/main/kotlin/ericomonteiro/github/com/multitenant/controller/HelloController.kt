package ericomonteiro.github.com.multitenant.controller

import ericomonteiro.github.com.multitenant.config.TenantContext
import ericomonteiro.github.com.multitenant.config.datasource.DataSourceConfig
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/home")
class HelloController(
    private val dataSourceConfig: DataSourceConfig
) {

    @GetMapping
    fun showTenant() = ResponseEntity.ok(TenantContext.currentTenant)

    @GetMapping("/tenants")
    fun showTenantsConfig() = ResponseEntity.ok(dataSourceConfig)

}
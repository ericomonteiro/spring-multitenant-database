package ericomonteiro.github.com.multitenant.config.datasource

import ericomonteiro.github.com.multitenant.config.TenantContext
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class MultitenantDataSource: AbstractRoutingDataSource() {

    override fun determineCurrentLookupKey(): String? {
        return TenantContext.currentTenant
    }
}
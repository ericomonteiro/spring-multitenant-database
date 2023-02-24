package ericomonteiro.github.com.multitenant.config

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class MultitenantDataSource: AbstractRoutingDataSource() {

    override fun determineCurrentLookupKey(): String? {
        return TenantContext.currentTenant
    }
}
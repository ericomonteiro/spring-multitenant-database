package ericomonteiro.github.com.multitenant.config

import ericomonteiro.github.com.multitenant.constants.Headers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC

object TenantContext {
    private val CURRENT_TENANT = ThreadLocal<String>()
    private val logger: Logger = LoggerFactory.getLogger(TenantContext::class.java)
    var currentTenant: String
        get() = CURRENT_TENANT.get() ?: ""
        set(tenant) {
            logger.info("setting tenant to tenant=$tenant")
            MDC.put(Headers.TENANT_ID, tenant)
            CURRENT_TENANT.set(tenant)
        }
}
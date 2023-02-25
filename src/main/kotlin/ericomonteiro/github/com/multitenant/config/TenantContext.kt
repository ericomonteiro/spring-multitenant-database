package ericomonteiro.github.com.multitenant.config

object TenantContext {
    private val CURRENT_TENANT = ThreadLocal<String>()
    var currentTenant: String
        get() = CURRENT_TENANT.get() ?: ""
        set(tenant) {
            CURRENT_TENANT.set(tenant)
        }
}
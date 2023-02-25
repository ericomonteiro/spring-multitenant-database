package ericomonteiro.github.com.multitenant.config.http

import ericomonteiro.github.com.multitenant.config.TenantContext.currentTenant
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(0)
class TenantRequestFilter: Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val tenantName = req.getHeader("X-TenantID")
        currentTenant = tenantName

        try {
            chain.doFilter(request, response)
        } finally {
            currentTenant = ""
        }
    }
}
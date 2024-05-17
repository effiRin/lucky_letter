package org.lucky.letter.config

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.stereotype.Component

@Component
class AuthFilter() : Filter {

    private val allowOriginPath = "*"

    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain) {
        val request = (servletRequest as HttpServletRequest?)?.let { ServletServerHttpRequest(it) }
        val response = (servletResponse as HttpServletResponse?)?.let { ServletServerHttpResponse(it) }

        corsFilter(request!!, response!!)

        if (request.method == HttpMethod.OPTIONS) {
            response.servletResponse.status = HttpStatus.OK.value()
            return
        }

        chain.doFilter(servletRequest, servletResponse)
    }

    private fun corsFilter(request: ServletServerHttpRequest, response: ServletServerHttpResponse) {
        response.servletResponse.setHeader("Access-Control-Allow-Origin", "$allowOriginPath")
        response.servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
        response.servletResponse.setHeader("Access-Control-Max-Age", "600")
        response.servletResponse.setHeader(
            "Access-Control-Allow-Headers",
            "Origin, X-Requested-With, Content-Type, Accept, Authorization",
        )
    }
}

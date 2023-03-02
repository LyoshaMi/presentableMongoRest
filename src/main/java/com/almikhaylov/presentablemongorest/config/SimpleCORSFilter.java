package com.almikhaylov.presentablemongorest.config;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class SimpleCORSFilter implements Filter {

    public SimpleCORSFilter() {
        log.info("CORS Filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers",  "x-requested-with,Content-Type,Access-Control-Allow-Methods,Access-Control-Allow-Origin, Accept, Accept-Language, Content-Language, Content-Type, Authorization");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Max-Age", "4800");
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
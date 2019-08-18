package com.sundar.microservices.auth.configuration;

import brave.Span;
import brave.Tracer;
import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
public class CustomTraceFilter extends GenericFilterBean{

   private static final String TRACE_HEADER_NAME = "X-B3-TraceId";
    private final Tracer tracer;

    CustomTraceFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override public void doFilter(ServletRequest request, ServletResponse response,
                                   FilterChain chain) throws IOException, ServletException {
        Span currentSpan = this.tracer.currentSpan();
        if (currentSpan == null) {
            chain.doFilter(request, response);
            return;
        }
        // for readability we're returning trace id in a hex form
        ((HttpServletResponse) response)
                .addHeader(TRACE_HEADER_NAME,
                        currentSpan.context().traceIdString());
        // we can also add some custom tags
        // currentSpan.tag("custom", "tag");
        chain.doFilter(request, response);
    }
}

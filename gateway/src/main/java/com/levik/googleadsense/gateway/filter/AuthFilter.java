package com.levik.googleadsense.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levik.googleadsense.common.IdentityResponse;
import com.levik.googleadsense.gateway.service.AuthApi;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    private final AuthApi authApi;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthFilter(AuthApi authApi, ObjectMapper objectMapper) {
        this.authApi = authApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOG.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        String identity = request.getParameter("clientIdentity");

        if (identity != null) {
            IdentityResponse identityResponse = authApi.findIdentity(identity);
            String identityDetails = convertIdentityToSting(identityResponse);
            ctx.addZuulRequestHeader("auth", identityDetails);
        }

        return null;
    }

    public String convertIdentityToSting(IdentityResponse identityResponse) {
        String identityDetails = null;
        try {
            identityDetails = objectMapper.writeValueAsString(identityResponse);
        } catch (JsonProcessingException exe) {
            LOG.warn("Can't convert identity {}", exe.getMessage(), exe);
        }

        return identityDetails;
    }
}

package com.equipeRL.backend.Config;

import com.equipeRL.backend.Models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    static final Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

    private String usernameParameter = "username";
    private String passwordParameter = "password";

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        if (!req.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Metodo de autenticação não suportado: " + req.getMethod());
        }

        String username = obtainUsername(req);
        String password = obtainPassword(req);

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);//Collections.<GrantedAuthority>emptyList()

        // Allow subclasses to set the "details" property
        setDetails(req, authRequest);
        if(this.getAuthenticationManager() == null){
            logger.info("Authentication manager is null.");
        } else {
            logger.info("Authentication manager was "+this.getAuthenticationManager().getClass().getName());
        }

        return this.getAuthenticationManager().authenticate(authRequest);

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth
    ) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(res, auth.getName());
    }

    private String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    private String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    private void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    public final String getUsernameParameter() {
        return usernameParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }

}

package com.secondhand.secondhand.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public class SecurityServletFilter extends HttpFilter{
    
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        
        UsernamePasswordAuthenticationToken token = extUsernamePasswordfrom(request);

        if(notAuthenticated(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } if(notAuthorized(token, request)){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken extUsernamePasswordfrom(HttpServletRequest request){
        return extUsernamePasswordfrom(request);
    }

    private boolean notAuthenticated(UsernamePasswordAuthenticationToken token){
        return false;
    }

    private boolean notAuthorized(UsernamePasswordAuthenticationToken token, HttpServletRequest request){
        return false;
    }
}

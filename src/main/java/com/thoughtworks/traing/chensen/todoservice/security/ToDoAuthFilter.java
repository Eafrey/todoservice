package com.thoughtworks.traing.chensen.todoservice.security;

import com.google.common.collect.ImmutableList;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@lombok.extern.slf4j.Slf4j
@Component
public class ToDoAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("incoming request {}", request.getServletPath());

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.isEmpty(token)) {
            try {
                String[] tokens = token.split(":");
                int id = Integer.parseInt(tokens[0]);

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(id, null,
                                ImmutableList.of(new SimpleGrantedAuthority("admin"),
                                        new SimpleGrantedAuthority("role")))
                );
            } catch (Exception e) {
            } finally {
                filterChain.doFilter(request, response);
            }

        } else {
            filterChain.doFilter(request, response);
        }


    }

}

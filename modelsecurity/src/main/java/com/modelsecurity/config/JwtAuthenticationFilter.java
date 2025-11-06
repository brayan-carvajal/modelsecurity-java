package com.modelsecurity.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import com.modelsecurity.service.impl.JpaUserDetailsService;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JpaUserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtUtil jwtUtil, JpaUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header)) {
            String token;
            if (header.startsWith("Bearer ")) {
                token = header.substring(7).trim();
            } else {
                // accept raw token without "Bearer " prefix to support Swagger authorize input
                token = header.trim();
                logger.debug("Authorization header provided without 'Bearer ' prefix - treating value as raw token");
            }
            logger.debug("Found Authorization header, validating token (first8)={}", token.length() > 8 ? token.substring(0,8) : token);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsername(token);
                logger.debug("Token valid, username from token={}", username);
                var userDetails = userDetailsService.loadUserByUsername(username);
                // Use authorities from the loaded UserDetails to avoid mismatch between DB and token
                var authorities = userDetails.getAuthorities();
                logger.debug("Loaded UserDetails with {} authorities", authorities == null ? 0 : authorities.size());
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                logger.debug("Token validation failed for token startingWith={}...", token.substring(0, Math.min(8, token.length())));
            }
        }

        filterChain.doFilter(request, response);
    }
}

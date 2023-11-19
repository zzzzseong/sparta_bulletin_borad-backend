package com.sparta.spartabulletinboardbackend.config.filter;

import com.sparta.spartabulletinboardbackend.security.UserDetailsServiceImpl;
import com.sparta.spartabulletinboardbackend.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("=== PROCESS JWT AUTHORIZATION FILTER ===");

        String tokenValue = jwtService.getTokenFromRequest(request);
        System.out.println("tokenValue: " + tokenValue);

        if(StringUtils.hasText(tokenValue)) {
            String token = jwtService.substringToken(tokenValue);
            System.out.println("token: " + token);

            if(!jwtService.validateToken(token)) return;

            Claims userInfo = jwtService.getUserInfoFromToken(token);
            System.out.println("userInfo: " + userInfo.getSubject());

            try {
                setAuthentication(userInfo.getSubject());
            } catch(Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    public Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
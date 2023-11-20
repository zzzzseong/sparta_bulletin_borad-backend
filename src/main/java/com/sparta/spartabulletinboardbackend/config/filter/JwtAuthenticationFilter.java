//package com.sparta.spartabulletinboardbackend.config.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.spartabulletinboardbackend.domain.user.UserRole;
//import com.sparta.spartabulletinboardbackend.dto.user.UserLoginRequest;
//import com.sparta.spartabulletinboardbackend.security.UserDetailsImpl;
//import com.sparta.spartabulletinboardbackend.service.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//
//@Slf4j(topic = "로그인 및 JWT 생성")
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final JwtService jwtService;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        log.info("로그인 시도");
//        try {
//            UserLoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
//
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            loginRequest.getPassword(),
//                            null
//                    )
//            );
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        log.info("로그인 성공 및 JWT 생성");
//        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
//        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getUserRole();
//
//        String token = jwtService.createToken(username, role);
//        jwtService.addJwtToCookie(token, response);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        log.info("로그인 실패");
//        response.setStatus(401);
//    }
//}
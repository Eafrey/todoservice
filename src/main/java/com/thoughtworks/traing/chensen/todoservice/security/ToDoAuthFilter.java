package com.thoughtworks.traing.chensen.todoservice.security;

import com.google.common.collect.ImmutableList;
import com.thoughtworks.traing.chensen.todoservice.client.UserClient;
import com.thoughtworks.traing.chensen.todoservice.dto.User;
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

@Component
public class ToDoAuthFilter extends OncePerRequestFilter {

//    private static final byte[] SECRET_KEY = "kitty".getBytes(Charset.defaultCharset());

    @Autowired
    private UserClient userClient;
//
//    public static String generateToken(int id) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", id);
//
//        String token = Jwts.builder()
//                .addClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//        return token;
//    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.isEmpty(token)) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", token);

//            RestTemplate restTemplate = new RestTemplate();
//            String userResourceUrl = "http://localhost:8081/api/verification";

//                ResponseEntity<User> userResponse = restTemplate.postForEntity(userResourceUrl, token, User.class);
//                User userResponseBody = userResponse.getBody();
            try {
                User user = userClient.verifyToken(token);

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(user.getId(), null,
                                ImmutableList.of(new SimpleGrantedAuthority("admin"),
                                        new SimpleGrantedAuthority("role")))
                );
//                }
            } catch (Exception e) {
            } finally {
                filterChain.doFilter(request, response);
            }

        } else {
            filterChain.doFilter(request, response);
        }


//        SecurityContextHolder.getContext().setAuthentication(
//                new UsernamePasswordAuthenticationToken("user", null,
//                        ImmutableList.of(new SimpleGrantedAuthority("admin"),
//                                new SimpleGrantedAuthority("role")))
//        );

    }

}

package com.harshit.rav.config;

import com.harshit.rav.Util.JwtUtil;
import com.harshit.rav.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 1.  Get the token
        // 2. Check for bearer
        // 3. Validate the token

        String token = null;
        String email = null;
        System.out.println("line 37 auth filter");

        String authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println("line 41");
            token = authHeader.substring(7);
            email = jwtUtil.extractUsername(token);

            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(email);

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                System.out.println("line 48");
                if(jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            else{
                System.out.println("Token is not valid");
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

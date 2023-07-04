//package com.example.BlogMode.security;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private JwtTokenHelper jwtTokenHelper;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        // 1. get token
//
//        String requestToken = request.getHeader("Authorization");
//        System.out.println(requestToken);
//        String username = null;
//        String token = null;
//
//        if (requestToken != null && requestToken.startsWith("Bearer ")) {
//            token = requestToken.substring(7);
//            System.out.println("This is token: " + token);
//
//            try {
//                username = jwtTokenHelper.getUsernameFromToken(token);
//                System.out.println("Reached here");
//            } catch (IllegalArgumentException e) {
//                System.out.println("Unable to get Jwt token");
//            } catch (ExpiredJwtException e) {
//                System.out.println("Jwt token has expired");
//            } catch (MalformedJwtException e) {
//                System.out.println("Invalid Jwt");
//            }
//        } else {
//            System.out.println("Jwt token does not begin with Bearer");
//        }
//
//
//        // Once we get the token, now validate
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
//        {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if(jwtTokenHelper.validateToken(token,userDetails))
//            {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//            else {
//                System.out.println("Invalid Jwt token");
//            }
//        }
//        else
//        {
//            System.out.println("username is null or context is not null");
//        }
//
//        filterChain.doFilter(request,response);
//    }
//}

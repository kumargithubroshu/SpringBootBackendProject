//package com.example.BlogMode.controller;
//
//import com.example.BlogMode.payload.JwtAuthRequest;
//import com.example.BlogMode.payload.JwtAuthResponse;
//import com.example.BlogMode.security.JwtTokenHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private JwtTokenHelper jwtTokenHelper;
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthResponse> createLogin(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
//        System.out.println(jwtAuthRequest.getUsername() +" : " + " This is user name");
//        authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
//        System.out.println(jwtAuthRequest.getUsername() +" : " + " This is user name");
//        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
//        String token = jwtTokenHelper.generateToken(userDetails);
////        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
////        jwtAuthResponse.setToken(token);
////        return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
//        return ResponseEntity.ok(new JwtAuthResponse(token));
//    }
//
//    private void authenticate(String username, String password) throws Exception {
////        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, password);
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        }catch(BadCredentialsException e)
//        {
//            throw new Exception("Invalid username or password ",e);
//        }
//    }
//}

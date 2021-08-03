package com.harshit.rav.controller;


import com.harshit.rav.Util.JwtUtil;
import com.harshit.rav.dto.AuthDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.repository.AccountRepository;
import com.harshit.rav.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @GetMapping("/")
    public String welcome(){
        return "Welcome to RAV";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authRequest){

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);

    }

}

package com.ftn.backend.controller;

import com.ftn.backend.model.User;
import com.ftn.backend.security.TokenUtils;
import com.ftn.backend.security.auth.JwtAuthenticationRequest;
import com.ftn.backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {


    @Autowired
    private TokenUtils tokenUtils;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
     try {
         final Authentication authentication = authenticationManager.authenticate(token);


         // Ubaci username + password u kontext
         SecurityContextHolder.getContext().setAuthentication(authentication);

         // Kreiraj token
         User user = (User) authentication.getPrincipal();
         String jwt = tokenUtils.generateToken(user);
         int expiresIn = tokenUtils.getExpiredIn();

         // new UserTokenState(jwt, expiresIn)
         // Vrati token kao odgovor na uspesno autentifikaciju
         HttpHeaders headers = new HttpHeaders();
         headers.add("Authorization: Bearer", jwt);

         return ResponseEntity.ok().headers(headers).build();
     } catch (Exception e){
         return ResponseEntity.notFound().build();
     }

    }

}

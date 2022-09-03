/*
Unused provider class
 */

package com.example.demo.security;

import com.example.demo.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, Serializable {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        if (
                Objects.equals(
                        this.customUserDetailService.loadUserByUsername(username).getPassword(),
                        encodedPassword
                )
        ) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    encodedPassword,
                    new ArrayList<>()
            );
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
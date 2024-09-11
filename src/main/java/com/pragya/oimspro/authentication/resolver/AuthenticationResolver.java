package com.pragya.oimspro.authentication.resolver;

import com.pragya.oimspro.authentication.dto.JwtAuthenticationResponse;
import com.pragya.oimspro.authentication.dto.SignInRequest;
import com.pragya.oimspro.authentication.dto.SignUpRequest;
import com.pragya.oimspro.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AuthenticationResolver {
    private final AuthenticationService authenticationService;

    @PostMapping("signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}

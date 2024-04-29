package com.pragya.oimspro.user.service;

import com.pragya.oimspro.authentication.dto.JwtAuthenticationResponse;
import com.pragya.oimspro.jwt.service.JwtService;
import com.pragya.oimspro.user.entity.Role;
import com.pragya.oimspro.user.entity.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {
    private static final Logger Logger = org.slf4j.LoggerFactory.getLogger(UserInitializer.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Override
    public void run(String... args) throws Exception {
        if (userService.getTotalUser() == 0) {
            User user = User.builder()
                    .firstName("Admin")
                    .emailId("admin@gmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ROLE_ADMIN)
                    .build();
            var jwt = jwtService.generateToken(user);
            Logger.info("Admin token is: " + jwt);
            userService.createUser(user);
        }
    }
}

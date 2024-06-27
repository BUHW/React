package com.costs.costs_api.controller;

import com.costs.costs_api.domain.login.LoginRequestDTO;
import com.costs.costs_api.domain.login.RegisterRequestDTO;
import com.costs.costs_api.domain.login.ResponseDTO;
import com.costs.costs_api.domain.user.User;
import com.costs.costs_api.infra.security.TokenService;
import com.costs.costs_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = this.userRepository.findByLogin(body.login()).orElseThrow(() -> new RuntimeException("Login not found"));
        if ( passwordEncoder.matches(body.password(), user.getPassword()) ) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getLogin(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}

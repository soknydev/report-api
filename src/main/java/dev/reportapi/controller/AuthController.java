package dev.reportapi.controller;

import dev.reportapi.dto.auth.AuthResponse;
import dev.reportapi.dto.auth.LoginRequest;
import dev.reportapi.dto.auth.RefreshTokenRequest;
import dev.reportapi.dto.auth.RegisterRequest;
import dev.reportapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/refreshToken")
    AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refresh(refreshTokenRequest);
    }

    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

}

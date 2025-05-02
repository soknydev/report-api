package dev.reportapi.service;


import dev.reportapi.dto.auth.AuthResponse;
import dev.reportapi.dto.auth.LoginRequest;
import dev.reportapi.dto.auth.RefreshTokenRequest;
import dev.reportapi.dto.auth.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest registerRequest);

    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);

    AuthResponse login(LoginRequest loginRequest);

}

package dev.reportapi.service;


import dev.reportapi.dto.AuthResponse;
import dev.reportapi.dto.LoginRequest;
import dev.reportapi.dto.RefreshTokenRequest;
import dev.reportapi.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest registerRequest);

    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);

    AuthResponse login(LoginRequest loginRequest);

}

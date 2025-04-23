package dev.reportapi.service;


import dev.reportapi.dto.AuthResponse;
import org.springframework.security.core.Authentication;

public interface TokenService {

    AuthResponse createToken(Authentication auth);

    String createAccessToken(Authentication auth);

    String createRefreshToken(Authentication auth);

}

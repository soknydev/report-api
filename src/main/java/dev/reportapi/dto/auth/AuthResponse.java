package dev.reportapi.dto.auth;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}

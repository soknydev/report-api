package dev.reportapi.dto;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}

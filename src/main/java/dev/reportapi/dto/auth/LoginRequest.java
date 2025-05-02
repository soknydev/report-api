package dev.reportapi.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Passwordd is requerd")
        String password
) {
}

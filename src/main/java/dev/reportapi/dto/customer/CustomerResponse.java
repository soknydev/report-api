package dev.reportapi.dto.customer;

public record CustomerResponse(
        String customerId,
        String fullName,
        String email,
        String phone
) {
}

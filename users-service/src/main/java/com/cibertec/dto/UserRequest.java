package com.cibertec.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record UserRequest(
        @Email(message = "Email no tiene formato valido")
        @NotBlank(message = "Email no debe ser nulo")
        String email,
        @NotBlank(message = "Password no debe ser nulo")
        String password,
        @NotBlank(message = "Username no debe ser nulo")
        String username,
        @NotBlank(message = "FullName no debe ser nulo")
        String fullName,
        @NotBlank(message = "Status no debe ser nulo")
        String status,
        List<Long> roleIds
) {
}

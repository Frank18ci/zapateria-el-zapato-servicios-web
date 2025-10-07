package com.cibertec.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserResponse(
        Long id,
        String email,
        String password,
        String username,
        String fullName,
        String status,
        List<RoleResponse> roles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}

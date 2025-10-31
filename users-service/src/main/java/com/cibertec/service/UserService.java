package com.cibertec.service;

import com.cibertec.dto.UserRequest;
import com.cibertec.dto.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);

    Page<UserResponse> getAllPaged(int page, int size, String sortBy, String direction, String email);

    UserResponse updateKeyCloakId(Long id, String keycloakId);
}

package com.cibertec.service.impl;

import com.cibertec.dto.UserRequest;
import com.cibertec.dto.UserResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Role;
import com.cibertec.model.User;
import com.cibertec.repository.RoleRepository;
import com.cibertec.repository.UserRepository;
import com.cibertec.service.UserService;
import com.cibertec.util.SortDirectionDefault;
import com.cibertec.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id: " + id)
        ));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userRequest, roleRepository)));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User userFound = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id: " + id)
        );

        userFound.setEmail(userRequest.email());
        userFound.setPassword(userRequest.password());
        userFound.setUsername(userRequest.username());
        userFound.setFullName(userRequest.fullName());
        userFound.setStatus(userRequest.status());
        userFound.setRoles(userMapper.map(userRequest.roleIds(), roleRepository));

        return userMapper.toDto(userRepository.save(userFound));
    }

    @Override
    public void deleteUser(Long id) {
        User userFound = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id: " + id)
        );
        userRepository.delete(userFound);
    }

    @Override
    public Page<UserResponse> getAllPaged(int page, int size, String sortBy, String direction, String email) {
        Sort sort = Sort.by(SortDirectionDefault.getSortDirection(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> users = userRepository.findByEmailContaining(email, pageable);
        return users.map(userMapper::toDto);
    }
}

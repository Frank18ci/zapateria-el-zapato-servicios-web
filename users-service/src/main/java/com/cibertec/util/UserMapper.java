package com.cibertec.util;

import com.cibertec.dto.UserRequest;
import com.cibertec.dto.UserResponse;
import com.cibertec.model.Role;
import com.cibertec.model.User;
import com.cibertec.repository.RoleRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    @Mapping(target = "roles", source = "roleIds")
    User toEntity(UserRequest user, @Context RoleRepository roleRepository);
    UserResponse toDto(User user);
    List<UserResponse> toDtoList(List<User> users);

    default List<Role> map(List<Long> roleIds, @Context RoleRepository roleRepository) {
        if(roleIds == null || roleIds.isEmpty()) {
            return List.of();
        }
        return roleRepository.findAllById(roleIds);
    }
}

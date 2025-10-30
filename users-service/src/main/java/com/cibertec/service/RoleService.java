package com.cibertec.service;

import com.cibertec.dto.RoleRequest;
import com.cibertec.dto.RoleResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse getRoleById(Long id);
    RoleResponse createRole(RoleRequest roleRequest);
    RoleResponse updateRole(Long id, RoleRequest roleRequest);
    void deleteRole(Long id);

    Page<RoleResponse> getAllPaged(int page, int size, String sortBy, String direction, String name);
}

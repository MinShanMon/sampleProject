package edu.nus.iss.Clubmvc.controller.service;

import java.util.List;

import edu.nus.iss.Clubmvc.model.Role;

public interface RoleService {
    List<Role> findAllRoles();
    Role findRole(String roleId);
    Role createRole(Role role);
    Role changeRole(Role role);
    void removeRole(Role role);
    List<String> findAllRolesNames();
    List<Role> findRoleByName(String name);
}

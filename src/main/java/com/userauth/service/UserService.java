package com.userauth.service;

import com.userauth.entity.Role;
import com.userauth.entity.User;

import java.util.List;

public interface UserService {
    void createUser(String username, String password);
    void deleteUser(User user);
    Role createRole(String roleName);
    void deleteRole(String roleName);
    void addRole(User user, String roleName);
    String authenticate(String username, String password);
    void invalidate(String token);
    Boolean checkRole(String token, Role role);
    List<Role> allRoles(String token);
}

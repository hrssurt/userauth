package com.userauth.UserServiceTests;

import com.userauth.entity.User;
import com.userauth.entity.Role;
import com.userauth.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    String testUsername = "ab";
    String testPassword = "12";
    String testRoleName = "basic";
    User testUser;
    Role testRole;
    String testToken = "secret";

    @Test
    void testCreateUser() {
        userService.createUser(testUsername,testPassword);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(testUser);
    }

    @Test
    void testCreateRole() {
        userService.createRole(testRoleName);
    }

    @Test
    void testDeleteRole() {
        userService.deleteRole(testRoleName);
    }

    @Test
    void testAddRole() {
        userService.addRole(testUser,testRoleName);
    }

    @Test
    void testAuthenticate() {
        userService.authenticate(testUsername,testPassword);
    }

    @Test
    void testInvalidate() {
        userService.invalidate(testToken);
    }

    @Test
    void testCheckRoles() {
        userService.checkRole(testToken,testRole);
    }

    @Test
    void testAllRoles() {
        userService.allRoles(testToken);
    }
}

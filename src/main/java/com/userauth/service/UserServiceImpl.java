package com.userauth.service;

import com.userauth.entity.Role;
import com.userauth.entity.User;
import com.userauth.service.exception.*;
import org.apache.tomcat.util.net.openssl.ciphers.Encryption;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    List<User> userList;
    Map<String,Role> roleList;
    Map<String, User> userToken;

    public void createUser(String username, String password){
        try{
            User user = new User(username, password, new ArrayList<Role>(){},Instant.now());
            userList.add(user);
        } catch (Exception e){
            throw new UserExistException("User already exists");
        }
    }

    public void deleteUser(User user) {
        if(userList.contains(user)) {
            userList.remove(user);
        } else {
            throw new UserNotFoundException("User not found.");
        }
    }

    public Role createRole(String roleName) {
        try{
            if(roleList.containsKey(roleName)) {
                throw new RoleExistException("Role already exists");
            } else {
                Role role = new Role(roleName);
                roleList.put(roleName, role);
                return role;
            }
        } catch (Exception e) {
            throw new ServiceException("Something went wrong");
        }
    }

    public void deleteRole(String roleName) {
        if(roleList.containsKey(roleName)) {
            roleList.remove(roleName);
        } else {
            throw new RoleNotFoundException("Role was not found");
        }
    }

    public void addRole(User user, String roleName) {
        Role role = roleList.get(roleName);
        List<Role> userRoles = user.getRoles();
        if(!userRoles.contains(role)) {
            userRoles.add(role);
            user.setRoles(userRoles);
        } else return;
    }

    public String authenticate(String username, String password) {

        try{
            String token = encryptToken(username+password);
            User user = new User(username,password,new ArrayList<Role>(){},Instant.ofEpochSecond(60*60*2));
            return token;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void invalidate(String token) {
        try {
            userToken.remove(token);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Boolean checkRole(String token, Role role) {
        try{
            User user = userToken.get(token);
            return user.getRoles().contains(role);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Role> allRoles(String token) {
        try {
            User user = userToken.get(token);
            if(isTokenExpired(user)) return user.getRoles();
            else {
                throw new  RoleNotFoundException("Token expired");
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Boolean isTokenExpired(User user) {
        Instant tokenExpires = user.getTokenExpires();
        return Instant.now().isBefore(tokenExpires);
    }

    public String encryptToken(String userInfo){
        Encryption encryption = Encryption.valueOf(userInfo);
        return encryption.toString();
    }
}

package com.userauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    String username;
    String password;
    List<Role> roles;
    Instant TokenExpires;
}

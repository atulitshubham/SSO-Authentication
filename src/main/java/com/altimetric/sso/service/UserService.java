package com.altimetric.sso.service;

import com.altimetric.sso.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
}

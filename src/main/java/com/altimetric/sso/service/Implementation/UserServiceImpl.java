package com.altimetric.sso.service.Implementation;

import com.altimetric.sso.model.User;
import com.altimetric.sso.repository.UserRepo;
import com.altimetric.sso.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}

package com.altimetric.sso.service;

import com.altimetric.sso.dto.AuthResponseDto;
import com.altimetric.sso.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponseDto generateToken(UserDto userDto);
}

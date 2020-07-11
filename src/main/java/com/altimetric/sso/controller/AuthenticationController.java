package com.altimetric.sso.controller;

import com.altimetric.sso.dto.AuthResponseDto;
import com.altimetric.sso.dto.UserDto;
import com.altimetric.sso.model.User;
import com.altimetric.sso.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/generateToken")
    public AuthResponseDto generateToken(@Valid @RequestBody UserDto userDto) {
        log.info("user tried to generate the access token");
        return authService.generateToken(userDto);
    }

}

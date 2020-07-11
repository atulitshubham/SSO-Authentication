package com.altimetric.sso.service.Implementation;

import com.altimetric.sso.config.JWTVerifier;
import com.altimetric.sso.dto.AuthResponseDto;
import com.altimetric.sso.dto.UserDto;
import com.altimetric.sso.model.User;
import com.altimetric.sso.repository.UserRepo;
import com.altimetric.sso.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private JWTVerifier jwtVerifier;
    private UserRepo userRepo;

    public AuthServiceImpl(JWTVerifier jwtVerifier, UserRepo userRepo) {
        this.jwtVerifier = jwtVerifier;
        this.userRepo = userRepo;
    }

    @Override
    public AuthResponseDto generateToken(UserDto userDto) {
        AuthResponseDto authResponseDto = new AuthResponseDto();
        ModelMapper mapper = new ModelMapper();
        User user = new User();
        mapper.map(userDto, user);
        try {
            String accessToken = jwtVerifier.generateToken(user);
            log.info("JWT token generated successfully and added to response");
            User userPresent = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if(userPresent == null) {
                userRepo.save(user);
            }
            authResponseDto.setStatus("success");
            authResponseDto.setData(accessToken);
            log.info("User added to Db with ID :-" + user.getUserId());
        } catch (Exception e) {
            log.error("Some error occurred ", e);
            authResponseDto.setStatus("Error occured");
        }
        return authResponseDto;
    }
}

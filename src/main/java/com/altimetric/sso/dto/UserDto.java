package com.altimetric.sso.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class UserDto {
    @NotNull(message = "display name can not be null")
    private String email;
    @NotNull(message = "display name can not be null")
    private String password;
}

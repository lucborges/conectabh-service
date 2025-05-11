package com.pucminas.conectabh_service.controller.responses;

import com.pucminas.conectabh_service.controller.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Long expiresIn;
    private UserDto user;
}

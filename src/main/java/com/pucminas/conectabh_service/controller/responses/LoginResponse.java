package com.pucminas.conectabh_service.controller.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Long expiresIn;
}

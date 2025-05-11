package com.pucminas.conectabh_service.controller;

import com.pucminas.conectabh_service.adapter.Adapter;
import com.pucminas.conectabh_service.controller.dto.LoginUserDto;
import com.pucminas.conectabh_service.controller.dto.RegisterUserDto;
import com.pucminas.conectabh_service.controller.dto.UserDto;
import com.pucminas.conectabh_service.controller.responses.LoginResponse;
import com.pucminas.conectabh_service.domain.User;
import com.pucminas.conectabh_service.repository.data.UserData;
import com.pucminas.conectabh_service.security.JwtService;
import com.pucminas.conectabh_service.usecase.AuthenticationUserUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationUserUsecase authenticationUserUsecase;
    @Autowired
    Adapter<User, UserData> adapter;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationUserUsecase.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(adapter.convert(authenticatedUser));

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUser(authenticationUserUsecase.getUserFromToken(jwtToken));

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationUserUsecase.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        UserDto user = authenticationUserUsecase.getUserFromToken(token);
        return ResponseEntity.ok(user);
    }
}

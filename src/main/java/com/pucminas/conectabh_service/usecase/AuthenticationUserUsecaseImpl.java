package com.pucminas.conectabh_service.usecase;

import com.pucminas.conectabh_service.adapter.dataToEntity.UserDataToUser;
import com.pucminas.conectabh_service.adapter.entityToData.UserToUserData;
import com.pucminas.conectabh_service.controller.dto.LoginUserDto;
import com.pucminas.conectabh_service.controller.dto.RegisterUserDto;
import com.pucminas.conectabh_service.domain.User;
import com.pucminas.conectabh_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationUserUsecaseImpl implements AuthenticationUserUsecase{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDataToUser userDataToEntity;
    @Autowired
    UserToUserData userToUserData;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationUserUsecaseImpl.class);

    @Override
    public User signup(RegisterUserDto input) {
        try {
            User user = new User();
            user.setFullName(input.getFullName());
            user.setEmail(input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setRole(input.getRoleType());

            return userDataToEntity.convert(userRepository.save(userToUserData.convert(user)));
        } catch (Exception e) {
            logger.error("Error on try signup new user. user email: {}. err: {}", input.getEmail(), e.getMessage());
            throw e;
        }

    }

    @Override
    public User authenticate(LoginUserDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );

            return userDataToEntity.convert(userRepository.findByEmail(input.getEmail())
                    .orElseThrow());
        } catch (Exception e) {
            logger.error("Error on try authenticate. user email: {}. err: {}", input.getEmail(), e.getMessage());
            throw e;
        }
    }
}

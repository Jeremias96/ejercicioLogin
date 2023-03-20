package com.leiton.ejercicioLogin.impl;

import com.leiton.ejercicioLogin.dao.UserRepository;
import com.leiton.ejercicioLogin.dto.LoginRequest;
import com.leiton.ejercicioLogin.dto.LoginResponse;
import com.leiton.ejercicioLogin.dto.SignUpRequest;
import com.leiton.ejercicioLogin.dto.SignUpResponse;
import com.leiton.ejercicioLogin.entity.UserEntity;
import com.leiton.ejercicioLogin.exception.EmailValidationException;
import com.leiton.ejercicioLogin.exception.PasswordValidationException;
import com.leiton.ejercicioLogin.exception.UserAlreadyExistsException;
import com.leiton.ejercicioLogin.exception.UserNotFoundException;
import com.leiton.ejercicioLogin.mapper.UserMapper;
import com.leiton.ejercicioLogin.service.LoginService;
import com.leiton.ejercicioLogin.utils.EncryptUtils;
import com.leiton.ejercicioLogin.utils.JwtUtils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) throws EmailValidationException, PasswordValidationException, GeneralSecurityException, 
        UnsupportedEncodingException, UserAlreadyExistsException {
        if (!Pattern.matches("^(.+)@(.+)[.](.+)$", signUpRequest.getEmail())){
            throw new EmailValidationException();
        }

        if (!Pattern.matches("^(?=(?:[^A-Z]*[A-Z]){1}[^A-Z]*$)(?=(?:[^0-9]*[0-9]){2}[^0-9]*$).{8,12}$", signUpRequest.getPassword())){
            throw new PasswordValidationException();
        }

        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        signUpRequest.setPassword(EncryptUtils.encrypt(signUpRequest.getPassword()));
        UserEntity userSaved = userRepository.save(UserMapper.INSTANCE.map(signUpRequest));

        return UserMapper.INSTANCE.map(userSaved);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByToken(loginRequest.getToken());

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        } else {
            user.get().setToken(JwtUtils.createJwt(user.get().getEmail()));
            user.get().setLastLogin(Instant.now());
        }
        UserEntity userUpdated = userRepository.save(user.get());
        return UserMapper.INSTANCE.mapToLoginResponse(userUpdated);
    }
    
}

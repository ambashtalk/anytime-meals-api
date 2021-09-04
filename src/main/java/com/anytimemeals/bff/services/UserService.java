package com.anytimemeals.bff.services;

import com.anytimemeals.bff.exceptions.UserCreationFailedException;
import com.anytimemeals.bff.exceptions.UserNotFoundException;
import com.anytimemeals.bff.exceptions.UserPasswordMismatchException;
import com.anytimemeals.bff.models.User;
import com.anytimemeals.bff.repository.UserRepository;
import com.anytimemeals.bff.request.LoginRequestBody;
import com.anytimemeals.bff.request.SignupRequestBody;
import com.anytimemeals.bff.response.SignUpResponse;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User authenticatedUser(LoginRequestBody loginRequestBody) {
        List<User> user = userRepository.findByUsername(loginRequestBody.getUsername());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        log.info(loginRequestBody.getPassword());
        log.info(user.get(0).getPassword());
        if (!loginRequestBody.getPassword().equals(user.get(0).getPassword())) {
            throw new UserPasswordMismatchException();
        }
        return user.get(0);
    }

    public SignUpResponse registerUser (SignupRequestBody signupRequestBody) {
        if (signupRequestBody.getUsername().length() < 4) {
            throw new UserCreationFailedException("Username cannot be empty");
        }
        if (signupRequestBody.getPassword().length() < 8) {
            throw new UserCreationFailedException("Password cannot be empty");
        }
        if (signupRequestBody.getEmail().length() == 0) {
            throw new UserCreationFailedException("Email cannot be empty");
        }

        List<User> existingUser = userRepository.findByUsername(signupRequestBody.getUsername());
        if (existingUser.stream().count() > 0) {
            throw new UserCreationFailedException("Username already in use");
        }

        User newUser = User.builder()
                .username(signupRequestBody.getUsername())
                .email(signupRequestBody.getEmail())
                .password(signupRequestBody.getPassword())
                .build();
        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UserCreationFailedException(e.getMessage());
        }

        return SignUpResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("User successfully created !!")
                .build();
    }
}

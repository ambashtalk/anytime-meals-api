package com.anytimemeals.bff.controllers;

import com.anytimemeals.bff.models.User;
import com.anytimemeals.bff.request.LoginRequestBody;
import com.anytimemeals.bff.request.SignupRequestBody;
import com.anytimemeals.bff.response.SignUpResponse;
import com.anytimemeals.bff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> authenticateUser(@RequestBody LoginRequestBody loginRequestBody) {
        return new ResponseEntity<>(userService.authenticatedUser(loginRequestBody), HttpStatus.OK);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SignUpResponse reegisterUser(@RequestBody SignupRequestBody signupRequestBody) {
        return userService.registerUser(signupRequestBody);
    }


}

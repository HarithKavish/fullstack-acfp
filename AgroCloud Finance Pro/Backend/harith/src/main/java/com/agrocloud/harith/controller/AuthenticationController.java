package com.agrocloud.harith.controller;

import static com.agrocloud.harith.Utils.MyConstant.AUTH;
import static com.agrocloud.harith.Utils.MyConstant.LOGIN;
import static com.agrocloud.harith.Utils.MyConstant.REGISTER;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrocloud.harith.dto.request.LoginRequest;
import com.agrocloud.harith.dto.request.RegisterRequest;
import com.agrocloud.harith.dto.response.LoginResponse;
import com.agrocloud.harith.dto.response.RegisterResponse;
import com.agrocloud.harith.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService AuthenticationService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        RegisterResponse response = new RegisterResponse();
        try {
            response = AuthenticationService.register(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Something went wrong!");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = new LoginResponse();
        try {
            response = AuthenticationService.login(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            // LoginResponse.builder()
            // .message("Something went wrong!").token("").build();
            // return new ResponseEntity<>(response, EXPECTATION_FAILED);
            System.out.println(e);
            response.setMessage("Something went wrong!");
            response.setToken("");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }
}
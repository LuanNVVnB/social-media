package com.example.cnd.controller;

import com.example.cnd.common.base.SuccessResponse;
import com.example.cnd.request.AuthenticationRequest;
import com.example.cnd.request.RegisterRequest;
import com.example.cnd.response.AuthenticationResponse;
import com.example.cnd.service.AuthenticationServices;
import com.example.cnd.validator.BindingRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * File: com.example.cnd.controller
 * Description:  <div style="font-weight: bold; color: #0073e6;"> AuthenticationController </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServices authenticationService;
    private final BindingRequest bindingRequest;

    /**
     * Controller register
     */
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody RegisterRequest request,
            BindingResult br) {
        bindingRequest.processValidateBindingResult(br);
        AuthenticationResponse response = authenticationService.register(request);

        return ResponseEntity.ok(new SuccessResponse(response));
    }


    /**
     * Controller login user and authentication
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request,
            BindingResult br) {
        bindingRequest.processValidateBindingResult(br);
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }


}
package com.example.cnd.controller;

import com.example.cnd.common.base.SuccessResponse;
import com.example.cnd.request.ProfileRequest;
import com.example.cnd.request.RegisterRequest;
import com.example.cnd.response.AuthenticationResponse;
import com.example.cnd.service.UserServices;
import com.example.cnd.validator.BindingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * File: com.example.cnd.controller
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UserCon troller </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userService;
    private final BindingRequest bindingRequest;

    /**
     * Controller update information user or profile
     */
    @PutMapping("/profile")
    public ResponseEntity<SuccessResponse> updateProfile(@Valid @RequestBody ProfileRequest request,
            BindingResult br) {
        bindingRequest.processValidateBindingResult(br);
        AuthenticationResponse response = userService.updateProfile(request);

        return ResponseEntity.ok(new SuccessResponse(response));
    }
}

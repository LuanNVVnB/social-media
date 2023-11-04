package com.example.cnd.service;

import com.example.cnd.request.ProfileRequest;
import com.example.cnd.response.AuthenticationResponse;

/**
 * File: com.example.cnd.service
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UserServices </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
public interface UserServices {
    AuthenticationResponse updateProfile(ProfileRequest request);
}

package com.example.cnd.service.impl;

import com.example.cnd.dao.entity.User;
import com.example.cnd.dao.repository.UserRepository;
import com.example.cnd.request.ProfileRequest;
import com.example.cnd.request.UsersRequest;
import com.example.cnd.response.AuthenticationResponse;
import com.example.cnd.response.UsersResponse;
import com.example.cnd.service.UserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * File: com.example.cnd.service.impl
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UserServiceImpl </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserServices {
    private final UserRepository userRepository;

    @Override
    public AuthenticationResponse updateProfile(ProfileRequest request) {
        return null;
    }

    @Override
    public List<UsersResponse> getUsers(UsersRequest request) {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(item -> new UsersResponse().builder()
                        .userName(item.getUsername())
                        .build())
                .toList();
    }
}

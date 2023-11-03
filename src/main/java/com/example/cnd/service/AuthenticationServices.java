package com.example.cnd.service;

import com.example.cnd.request.AuthenticationRequest;
import com.example.cnd.request.RegisterRequest;
import com.example.cnd.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * File: com.example.cnd.service
 * Description:  <div style="font-weight: bold; color: #0073e6;"> Authentication Services </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
public interface AuthenticationServices {

    /**
     * Refreshes the authentication token for the user.
     * <p>
     * This method handles the process of refreshing the user's authentication token using a refresh token.
     *
     * @param request  The HTTP request object containing the user's current authentication token.
     * @param response The HTTP response object used to send the new authentication token.
     * @throws IOException If there is an issue with input/output during the token refresh.
     */
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Authenticates a user and generates authentication and refresh tokens.
     * <p>
     * This method handles user authentication using the provided credentials (email and password) and generates
     * both an authentication token and a refresh token for the user.
     *
     * @param request The authentication request containing the user's email and password.
     * @return An AuthenticationResponse containing the newly generated authentication and refresh tokens.
     * @throws javax.naming.AuthenticationException If authentication fails due to invalid credentials.
     */
    AuthenticationResponse login(AuthenticationRequest request);

    /**
     * Registers a new user and generates authentication and refresh tokens.
     *
     * This method handles the process of registering a new user, saving their information to the database,
     * and generating both an authentication token and a refresh token for the newly registered user.
     *
     * @param request The registration request containing user information.
     * @return An AuthenticationResponse containing the newly generated authentication and refresh tokens.
     * @throws com.example.cnd.exception.ServerErrorException If an error occurs during the registration process.
     */
    AuthenticationResponse register(RegisterRequest request);
}

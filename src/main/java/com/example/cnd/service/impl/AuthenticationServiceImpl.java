package com.example.cnd.service.impl;

import com.example.cnd.common.enums.MessageError;
import com.example.cnd.common.enums.RoleEnum;
import com.example.cnd.common.enums.TokenEnum;
import com.example.cnd.config.JwtService;
import com.example.cnd.dao.entity.Token;
import com.example.cnd.dao.entity.User;
import com.example.cnd.dao.entity.UserDetail;
import com.example.cnd.dao.repository.TokenRepository;
import com.example.cnd.dao.repository.UserDetailRepository;
import com.example.cnd.dao.repository.UserRepository;
import com.example.cnd.exception.BadRequestException;
import com.example.cnd.request.AuthenticationRequest;
import com.example.cnd.request.RegisterRequest;
import com.example.cnd.response.AuthenticationResponse;
import com.example.cnd.service.AuthenticationServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationServices {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserDetailRepository userDetailRepository;

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        Optional<Long> isExist = userDetailRepository.existsByUserNameOrEmail(request.getUserName());
        if (isExist.isPresent()) {
            throw new BadRequestException(MessageError.findErrorById(MessageError.E_400));
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roleEnum(RoleEnum.USER)
                .build();
        userRepository.save(user);

        var userDetail = UserDetail.builder()
                .user(user)
                .birthDate(request.getBirthday())
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .userName(request.getUserName())
                .address(request.getAddress())
                .build();

        userDetailRepository.save(userDetail);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenEnum(TokenEnum.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }


    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
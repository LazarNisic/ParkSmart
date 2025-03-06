package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.configuration.AppConfiguration;
import com.lazarnisic.ParkSmart.dto.UserDTO;
import com.lazarnisic.ParkSmart.enums.TokenGrantType;
import com.lazarnisic.ParkSmart.exception.BadCredentials;
import com.lazarnisic.ParkSmart.exception.UserNotFound;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.User;
import com.lazarnisic.ParkSmart.repository.UserRepository;
import com.lazarnisic.ParkSmart.security.JwtUtil;
import com.lazarnisic.ParkSmart.service.AuthService;
import com.lazarnisic.ParkSmart.service.data.AuthenticateRequest;
import com.lazarnisic.ParkSmart.service.data.AuthenticateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.BadCredentialsException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AppConfiguration appConfiguration;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) {
        User user;
        String jwtSecret = appConfiguration.getJwt().getSecret();
        if (authenticateRequest.getGrantType().equals(TokenGrantType.ACCESS)) {
            user = userRepository.findByUsername(authenticateRequest.getUsername()).orElseThrow(() -> new UserNotFound(authenticateRequest.getUsername()));
        } else {
            final Claims claims = jwtUtil.authenticateToken(authenticateRequest.getRefreshToken(), TokenGrantType.REFRESH, jwtSecret);
            Long userId = claims.get("userId", Long.class);
            user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
        }
        UserDTO userDTO = userMapper.toDto(user);

        long accessExpirationMs = appConfiguration.getJwt().getAccessExpirationMs();
        long refreshExpirationMs = appConfiguration.getJwt().getRefreshExpirationMs();
        if (authenticateRequest.getGrantType().equals(TokenGrantType.ACCESS)) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), authenticateRequest.getPassword()));
            } catch (BadCredentialsException e) {
                return new AuthenticateResponse(new BadCredentials());
            } catch (Exception e) {
                return new AuthenticateResponse(e);
            }
        }
        final String accessJwt = jwtUtil.generateAccessToken(userDTO, accessExpirationMs, jwtSecret);
        final String refreshJwt = jwtUtil.generateRefreshToken(userDTO, refreshExpirationMs, jwtSecret);
        LocalDateTime accessTokenValidUntil = jwtUtil.extractExpiration(accessJwt, jwtSecret);
        LocalDateTime refreshTokenValidUntil = jwtUtil.extractExpiration(refreshJwt, jwtSecret);
        return new AuthenticateResponse(null, accessJwt, refreshJwt, accessTokenValidUntil, refreshTokenValidUntil, userDTO);
    }
}

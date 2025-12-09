package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.UserDTO;
import com.lazarnisic.ParkSmart.exception.BadCredentials;
import com.lazarnisic.ParkSmart.exception.OldPasswordException;
import com.lazarnisic.ParkSmart.exception.PasswordsMatchException;
import com.lazarnisic.ParkSmart.exception.UserNotFound;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.User;
import com.lazarnisic.ParkSmart.repository.UserRepository;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDTO getAuthenticatedUser() {
        return findByUsername(getAuthenticationName());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound(username)));
    }

    @Override
    @Transactional
    public UserDTO changePassword(ChangePasswordRequest changePasswordRequest) {
        UserDTO userDTO = getAuthenticatedUser();
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserNotFound(userDTO.getId()));
        comparePasswords(changePasswordRequest);
        isOldPassword(user, changePasswordRequest.getOldPassword());
        return updatePassword(user, changePasswordRequest.getNewPassword());
    }

    private String getAuthenticationName() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            throw new BadCredentials();
        }
        return authentication.getName();
    }

    private void comparePasswords(ChangePasswordRequest changePasswordRequest) {
        if (changePasswordRequest.getOldPassword().equals(changePasswordRequest.getNewPassword())) {
            throw new PasswordsMatchException();
        }
    }

    private void isOldPassword(User user, String oldPassword) {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new OldPasswordException();
        }
    }

    private UserDTO updatePassword(User user, String password) {
        final String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        return userMapper.toDto(userRepository.save(user));
    }
}

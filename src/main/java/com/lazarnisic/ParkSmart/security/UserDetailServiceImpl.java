package com.lazarnisic.ParkSmart.security;

import com.lazarnisic.ParkSmart.exception.UserIsNotActive;
import com.lazarnisic.ParkSmart.exception.UserNotFound;
import com.lazarnisic.ParkSmart.model.User;
import com.lazarnisic.ParkSmart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound(username));
        if (Boolean.FALSE.equals(user.getEnabled())) {
            throw new UserIsNotActive(username);
        }
        return new UserPrincipal(user);
    }
}

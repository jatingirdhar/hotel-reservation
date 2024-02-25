package com.org.hotel.service.impl;

import com.org.hotel.config.CustomUserDetails;
import com.org.hotel.model.User;
import com.org.hotel.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findByUsername(username);
        User user = userOptional.orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username));
        // Load additional information like user ID from the database
        Long userId = user.getId();
        return new CustomUserDetails(user.getUsername(), user.getPassword(), userId, Collections.emptyList());
    }
}

package com.example.BlogMode.security;

import com.example.BlogMode.exception.ResourceNotFoundException;
import com.example.BlogMode.model.User;
import com.example.BlogMode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // loading user from database by username
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "UserEmail" + username, 0));


        return user;
    }
}

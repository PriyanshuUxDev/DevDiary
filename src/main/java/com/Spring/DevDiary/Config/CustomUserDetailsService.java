package com.Spring.DevDiary.Config;

import com.Spring.DevDiary.Entity.User;
import com.Spring.DevDiary.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameIgnoringCase(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userName);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
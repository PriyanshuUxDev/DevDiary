package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.DTO.auth.RegisterRequestDTO;
import com.Spring.DevDiary.Entity.User;
import com.Spring.DevDiary.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequestDTO request) {
        if (userRepository.findByUserNameIgnoringCase(request.getUserName()) != null) {
            return "Username already taken";
        }
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "Registered successfully";
    }
}
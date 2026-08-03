package com.Spring.DevDiary.Controller;

import com.Spring.DevDiary.DTO.auth.RegisterRequestDTO;
import com.Spring.DevDiary.Service.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequestDTO request) {
        return userAuthService.register(request);
    }
}
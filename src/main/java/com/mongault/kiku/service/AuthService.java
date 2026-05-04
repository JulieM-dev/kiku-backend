package com.mongault.kiku.service;

import com.mongault.kiku.dto.AuthResponseDto;
import com.mongault.kiku.dto.LoginDto;
import com.mongault.kiku.dto.RegisterDto;
import com.mongault.kiku.model.User;
import com.mongault.kiku.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto register(RegisterDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email already exist");
        }

        User user = User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponseDto(user.getDisplayName(), user.getEmail(), token);
    }

    public AuthResponseDto login(LoginDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);
        return new AuthResponseDto(user.getDisplayName(), user.getEmail(), token);
    }
}
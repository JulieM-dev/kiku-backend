package com.mongault.kiku.dto;

public record RegisterDto(
        String username,
        String email,
        String password
) {}
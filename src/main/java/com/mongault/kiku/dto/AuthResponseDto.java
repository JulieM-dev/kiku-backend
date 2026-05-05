package com.mongault.kiku.dto;

public record AuthResponseDto(
        String username,
        String email,
        String token
) {}
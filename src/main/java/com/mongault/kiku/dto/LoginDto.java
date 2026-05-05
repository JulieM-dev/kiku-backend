package com.mongault.kiku.dto;

public record LoginDto(
        String email,
        String password
) {}
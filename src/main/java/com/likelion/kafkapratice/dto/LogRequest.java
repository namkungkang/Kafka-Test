package com.likelion.kafkapratice.dto;

public record LogRequest(
        Long userId,
        String action,
        String keyword
) {
}
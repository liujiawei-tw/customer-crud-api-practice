package com.example.customerpractice.customer;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponse(
        String code,
        String message,
        List<String> details,
        LocalDateTime timestamp
) {
    public static ApiErrorResponse of(String code, String message) {
        return new ApiErrorResponse(
                code,
                message,
                List.of(),
                LocalDateTime.now()
        );
    }

    public static ApiErrorResponse of(String code, String message, List<String> details) {
        return new ApiErrorResponse(
                code,
                message,
                details,
                LocalDateTime.now()
        );
    }
}
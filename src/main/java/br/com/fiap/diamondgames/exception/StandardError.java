package br.com.fiap.diamondgames.exception;

import java.time.LocalDateTime;

public record StandardError(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}

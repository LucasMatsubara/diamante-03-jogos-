package br.com.fiap.diamondgames.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogadorRequestDTO(
        @NotBlank(message = "O nickname é obrigatório e não pode estar em branco.")
        String nickname,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Formato de e-mail inválido.")
        String email,

        @NotNull(message = "O nível é obrigatório.")
        @Min(value = 1, message = "O nível deve ser no mínimo 1.")
        Integer nivel
) {
}

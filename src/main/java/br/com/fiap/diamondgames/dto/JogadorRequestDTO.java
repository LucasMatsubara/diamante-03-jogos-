package br.com.fiap.diamondgames.dto;

public record JogadorRequestDTO(
        String nickname,
        String email,
        Integer nivel
) {
}

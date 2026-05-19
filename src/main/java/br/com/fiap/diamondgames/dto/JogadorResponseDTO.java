package br.com.fiap.diamondgames.dto;

public record JogadorResponseDTO(
        Long id,
        String nickname,
        String email,
        Integer nivel
) {
}

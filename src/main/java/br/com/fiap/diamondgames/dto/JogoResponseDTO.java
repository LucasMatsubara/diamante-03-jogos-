package br.com.fiap.diamondgames.dto;

import br.com.fiap.diamondgames.enums.Genero;

public record JogoResponseDTO(
        Long id,
        String titulo,
        Genero genero,
        String desenvolvedora
) {
}

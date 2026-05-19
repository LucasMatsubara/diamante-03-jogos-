package br.com.fiap.diamondgames.dto;

import java.time.LocalDateTime;

public record PartidaRequestDTO(
        Long jogadorId,
        Long jogoId,
        LocalDateTime dataHora,
        Integer pontuacao
) {
}

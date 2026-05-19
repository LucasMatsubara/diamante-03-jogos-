package br.com.fiap.diamondgames.dto;

import java.time.LocalDateTime;

public record PartidaResponseDTO(
        Long id,
        String nomeJogador,
        String tituloJogo,
        LocalDateTime dataHora,
        Integer pontuacao
) {
}

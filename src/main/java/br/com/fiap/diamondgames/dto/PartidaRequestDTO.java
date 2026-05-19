package br.com.fiap.diamondgames.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PartidaRequestDTO(
        @NotNull(message = "O ID do jogador é obrigatório para registrar uma partida.")
        Long jogadorId,

        @NotNull(message = "O ID do jogo é obrigatório para registrar uma partida.")
        Long jogoId,

        @NotNull(message = "A data e hora da partida são obrigatórias.")
        LocalDateTime dataHora,

        @NotNull(message = "A pontuação é obrigatória.")
        @Min(value = 0, message = "A pontuação não pode ser menor que zero.")
        Integer pontuacao
) {
}

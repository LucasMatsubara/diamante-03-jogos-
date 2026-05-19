package br.com.fiap.diamondgames.dto;
import br.com.fiap.diamondgames.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogoRequestDTO(
        @NotBlank(message = "O título do jogo é obrigatório.")
        String titulo,

        @NotNull(message = "O gênero é obrigatório.")
        Genero genero,

        @NotBlank(message = "A desenvolvedora é obrigatória.")
        String desenvolvedora
) {
}
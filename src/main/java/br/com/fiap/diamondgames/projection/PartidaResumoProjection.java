package br.com.fiap.diamondgames.projection;

import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;

public interface PartidaResumoProjection {
    Long getId();
    LocalDateTime getDataHora();
    Integer getPontuacao();

    @Value("#{target.jogador.nickname}")
    String getNomeJogador();

    @Value("#{target.jogo.titulo}")
    String getTituloJogo();
}

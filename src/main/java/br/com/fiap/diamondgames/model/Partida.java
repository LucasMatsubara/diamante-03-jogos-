package br.com.fiap.diamondgames.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    private LocalDateTime dataHora;
    private Integer pontuacao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Jogador getJogador() { return jogador; }
    public void setJogador(Jogador jogador) { this.jogador = jogador; }
    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Integer getPontuacao() { return pontuacao; }
    public void setPontuacao(Integer pontuacao) { this.pontuacao = pontuacao; }
}
package br.com.fiap.diamondgames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private Integer nivel;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Partida> partidas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public List<Partida> getPartidas() { return partidas; }
    public void setPartidas(List<Partida> partidas) { this.partidas = partidas; }
}

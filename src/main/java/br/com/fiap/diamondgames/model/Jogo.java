package br.com.fiap.diamondgames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    private String desenvolvedora;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Partida> partidas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getDesenvolvedora() { return desenvolvedora; }
    public void setDesenvolvedora(String desenvolvedora) { this.desenvolvedora = desenvolvedora; }
    public List<Partida> getPartidas() { return partidas; }
    public void setPartidas(List<Partida> partidas) { this.partidas = partidas; }
}

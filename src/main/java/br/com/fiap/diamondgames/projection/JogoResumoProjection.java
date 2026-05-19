package br.com.fiap.diamondgames.projection;

import br.com.fiap.diamondgames.enums.Genero;

public interface JogoResumoProjection {
    Long getId();
    String getTitulo();
    Genero getGenero();
}
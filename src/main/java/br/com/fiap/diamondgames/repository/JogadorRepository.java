package br.com.fiap.diamondgames.repository;

import br.com.fiap.diamondgames.model.Jogador;
import br.com.fiap.diamondgames.projection.JogadorResumoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    @Query("SELECT j.id as id, j.nickname as nickname, j.nivel as nivel FROM Jogador j")
    Page<JogadorResumoProjection> findAllResumo(Pageable pageable);
}

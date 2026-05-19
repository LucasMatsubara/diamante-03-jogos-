package br.com.fiap.diamondgames.repository;

import br.com.fiap.diamondgames.model.Partida;
import br.com.fiap.diamondgames.projection.PartidaResumoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    Page<PartidaResumoProjection> findAllProjectedBy(Pageable pageable);
}

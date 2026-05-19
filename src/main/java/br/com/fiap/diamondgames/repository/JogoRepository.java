package br.com.fiap.diamondgames.repository;

import br.com.fiap.diamondgames.model.Jogo;
import br.com.fiap.diamondgames.projection.JogoResumoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    Page<JogoResumoProjection> findAllProjectedBy(Pageable pageable);
}

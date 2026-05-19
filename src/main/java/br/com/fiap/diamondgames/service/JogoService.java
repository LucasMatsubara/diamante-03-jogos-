package br.com.fiap.diamondgames.service;

import br.com.fiap.diamondgames.model.Jogo;
import br.com.fiap.diamondgames.projection.JogoResumoProjection;
import br.com.fiap.diamondgames.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    public Page<JogoResumoProjection> listarPaginado(Pageable pageable) {
        return repository.findAllProjectedBy(pageable);
    }

    public Optional<Jogo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Jogo salvar(Jogo jogo) {
        return repository.save(jogo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public boolean existe(Long id) {
        return repository.existsById(id);
    }
}

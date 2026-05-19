package br.com.fiap.diamondgames.service;

import br.com.fiap.diamondgames.model.Jogador;
import br.com.fiap.diamondgames.projection.JogadorResumoProjection;
import br.com.fiap.diamondgames.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    public Page<JogadorResumoProjection> listarPaginado(Pageable pageable) {
        return repository.findAllResumo(pageable);
    }

    public Optional<Jogador> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Jogador salvar(Jogador jogador) {
        return repository.save(jogador);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public boolean existe(Long id) {
        return repository.existsById(id);
    }
}

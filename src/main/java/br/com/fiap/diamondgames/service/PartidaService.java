package br.com.fiap.diamondgames.service;

import br.com.fiap.diamondgames.dto.PartidaRequestDTO;
import br.com.fiap.diamondgames.model.Jogador;
import br.com.fiap.diamondgames.model.Jogo;
import br.com.fiap.diamondgames.model.Partida;
import br.com.fiap.diamondgames.projection.PartidaResumoProjection;
import br.com.fiap.diamondgames.repository.JogadorRepository;
import br.com.fiap.diamondgames.repository.JogoRepository;
import br.com.fiap.diamondgames.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private JogoRepository jogoRepository;

    public Page<PartidaResumoProjection> listarPaginadas(Pageable pageable) {
        return partidaRepository.findAllProjectedBy(pageable);
    }

    public Optional<Partida> buscarPorId(Long id) {
        return partidaRepository.findById(id);
    }

    public Partida salvar(PartidaRequestDTO dto, Long idOpcional) {
        Jogador jogador = jogadorRepository.findById(dto.jogadorId())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com ID: " + dto.jogadorId()));

        Jogo jogo = jogoRepository.findById(dto.jogoId())
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado com ID: " + dto.jogoId()));

        Partida partida = new Partida();
        if (idOpcional != null) {
            partida.setId(idOpcional);
        }
        partida.setJogador(jogador);
        partida.setJogo(jogo);
        partida.setDataHora(dto.dataHora());
        partida.setPontuacao(dto.pontuacao());

        return partidaRepository.save(partida);
    }

    public void deletar(Long id) {
        partidaRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return partidaRepository.existsById(id);
    }
}
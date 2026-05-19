package br.com.fiap.diamondgames.controller;

import br.com.fiap.diamondgames.dto.JogadorRequestDTO;
import br.com.fiap.diamondgames.dto.JogadorResponseDTO;
import br.com.fiap.diamondgames.exception.ResourceNotFoundException;
import br.com.fiap.diamondgames.model.Jogador;
import br.com.fiap.diamondgames.projection.JogadorResumoProjection;
import br.com.fiap.diamondgames.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService service;

    @GetMapping
    @Cacheable("jogadores_resumo")
    public ResponseEntity<Page<JogadorResumoProjection>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<JogadorResponseDTO>> buscarPorId(@PathVariable Long id) {
        Jogador jogador = service.buscarPorId(id);

        JogadorResponseDTO responseDTO = new JogadorResponseDTO(
                jogador.getId(), jogador.getNickname(), jogador.getEmail(), jogador.getNivel()
        );

        EntityModel<JogadorResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(linkTo(methodOn(JogadorController.class).listarTodos(Pageable.unpaged())).withRel("lista-jogadores"));
        resource.add(linkTo(methodOn(JogadorController.class).buscarPorId(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "jogadores_resumo", allEntries = true)
    public ResponseEntity<JogadorResponseDTO> criar(@RequestBody JogadorRequestDTO dto) {
        Jogador jogador = new Jogador();
        jogador.setNickname(dto.nickname());
        jogador.setEmail(dto.email());
        jogador.setNivel(dto.nivel());

        Jogador jogadorSalvo = service.salvar(jogador);

        JogadorResponseDTO response = new JogadorResponseDTO(
                jogadorSalvo.getId(), jogadorSalvo.getNickname(), jogadorSalvo.getEmail(), jogadorSalvo.getNivel()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "jogadores_resumo", allEntries = true)
    public ResponseEntity<JogadorResponseDTO> atualizar(@PathVariable Long id, @RequestBody JogadorRequestDTO dto) {
        if (!service.existe(id)) throw new ResourceNotFoundException("Jogador não encontrado. ID: " + id);

        Jogador jogadorAtualizado = new Jogador();
        jogadorAtualizado.setId(id);
        jogadorAtualizado.setNickname(dto.nickname());
        jogadorAtualizado.setEmail(dto.email());
        jogadorAtualizado.setNivel(dto.nivel());

        Jogador jogadorSalvo = service.salvar(jogadorAtualizado);

        JogadorResponseDTO response = new JogadorResponseDTO(
                jogadorSalvo.getId(), jogadorSalvo.getNickname(), jogadorSalvo.getEmail(), jogadorSalvo.getNivel()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "jogadores_resumo", allEntries = true)
    public void deletar(@PathVariable Long id) {
        service.deletar(id); // O Service agora valida e lança a exceção
    }
}
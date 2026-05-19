package br.com.fiap.diamondgames.controller;

import br.com.fiap.diamondgames.dto.PartidaRequestDTO;
import br.com.fiap.diamondgames.dto.PartidaResponseDTO;
import br.com.fiap.diamondgames.exception.ResourceNotFoundException;
import br.com.fiap.diamondgames.model.Partida;
import br.com.fiap.diamondgames.projection.PartidaResumoProjection;
import br.com.fiap.diamondgames.service.PartidaService;
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
@RequestMapping("/api/partidas")
public class PartidaController {

    @Autowired
    private PartidaService service;

    @GetMapping
    @Cacheable("partidas_resumo")
    public ResponseEntity<Page<PartidaResumoProjection>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginadas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PartidaResponseDTO>> buscarPorId(@PathVariable Long id) {
        Partida partida = service.buscarPorId(id);

        PartidaResponseDTO responseDTO = new PartidaResponseDTO(
                partida.getId(), partida.getJogador().getNickname(),
                partida.getJogo().getTitulo(), partida.getDataHora(), partida.getPontuacao()
        );

        EntityModel<PartidaResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(linkTo(methodOn(PartidaController.class).listarTodos(Pageable.unpaged())).withRel("lista-partidas"));
        resource.add(linkTo(methodOn(PartidaController.class).buscarPorId(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "partidas_resumo", allEntries = true)
    public ResponseEntity<PartidaResponseDTO> registrarPartida(@RequestBody PartidaRequestDTO dto) {
        Partida partidaSalva = service.salvar(dto, null);

        PartidaResponseDTO response = new PartidaResponseDTO(
                partidaSalva.getId(), partidaSalva.getJogador().getNickname(),
                partidaSalva.getJogo().getTitulo(), partidaSalva.getDataHora(), partidaSalva.getPontuacao()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "partidas_resumo", allEntries = true)
    public ResponseEntity<PartidaResponseDTO> atualizar(@PathVariable Long id, @RequestBody PartidaRequestDTO dto) {
        if (!service.existe(id)) throw new ResourceNotFoundException("Partida não encontrada. ID: " + id);

        Partida partidaAtualizada = service.salvar(dto, id);

        PartidaResponseDTO response = new PartidaResponseDTO(
                partidaAtualizada.getId(), partidaAtualizada.getJogador().getNickname(),
                partidaAtualizada.getJogo().getTitulo(), partidaAtualizada.getDataHora(), partidaAtualizada.getPontuacao()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "partidas_resumo", allEntries = true)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
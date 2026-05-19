package br.com.fiap.diamondgames.controller;

import br.com.fiap.diamondgames.dto.JogoRequestDTO;
import br.com.fiap.diamondgames.dto.JogoResponseDTO;
import br.com.fiap.diamondgames.exception.ResourceNotFoundException;
import br.com.fiap.diamondgames.model.Jogo;
import br.com.fiap.diamondgames.projection.JogoResumoProjection;
import br.com.fiap.diamondgames.service.JogoService;
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
@RequestMapping("/api/jogos")
public class JogoController {

    @Autowired
    private JogoService service;

    @GetMapping
    @Cacheable("jogos_resumo")
    public ResponseEntity<Page<JogoResumoProjection>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<JogoResponseDTO>> buscarPorId(@PathVariable Long id) {
        Jogo jogo = service.buscarPorId(id);

        JogoResponseDTO responseDTO = new JogoResponseDTO(jogo.getId(), jogo.getTitulo(), jogo.getGenero(), jogo.getDesenvolvedora());

        EntityModel<JogoResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(linkTo(methodOn(JogoController.class).listarTodos(Pageable.unpaged())).withRel("lista-jogos"));
        resource.add(linkTo(methodOn(JogoController.class).buscarPorId(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "jogos_resumo", allEntries = true)
    public ResponseEntity<JogoResponseDTO> criar(@RequestBody JogoRequestDTO dto) {
        Jogo jogo = new Jogo();
        jogo.setTitulo(dto.titulo());
        jogo.setGenero(dto.genero());
        jogo.setDesenvolvedora(dto.desenvolvedora());

        Jogo jogoSalvo = service.salvar(jogo);

        JogoResponseDTO response = new JogoResponseDTO(jogoSalvo.getId(), jogoSalvo.getTitulo(), jogoSalvo.getGenero(), jogoSalvo.getDesenvolvedora());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "jogos_resumo", allEntries = true)
    public ResponseEntity<JogoResponseDTO> atualizar(@PathVariable Long id, @RequestBody JogoRequestDTO dto) {
        if (!service.existe(id)) throw new ResourceNotFoundException("Jogo não encontrado. ID: " + id);

        Jogo jogoAtualizado = new Jogo();
        jogoAtualizado.setId(id);
        jogoAtualizado.setTitulo(dto.titulo());
        jogoAtualizado.setGenero(dto.genero());
        jogoAtualizado.setDesenvolvedora(dto.desenvolvedora());

        Jogo jogoSalvo = service.salvar(jogoAtualizado);

        JogoResponseDTO response = new JogoResponseDTO(jogoSalvo.getId(), jogoSalvo.getTitulo(), jogoSalvo.getGenero(), jogoSalvo.getDesenvolvedora());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "jogos_resumo", allEntries = true)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
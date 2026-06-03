package com.agencia.viagens.controller;

import com.agencia.viagens.dto.AvaliacaoRequestDTO;
import com.agencia.viagens.dto.DestinoRequestDTO;
import com.agencia.viagens.model.Destino;
import com.agencia.viagens.service.DestinoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para o recurso Destino.
 * Responsável apenas por receber requisições e delegar ao DestinoService.
 * Não contém lógica de negócio — boa prática e critério de avaliação do GR02.
 *
 * Endpoints:
 *   POST   /destinos              → Cadastrar destino
 *   GET    /destinos              → Listar todos
 *   GET    /destinos/pesquisar    → Pesquisar por nome ou localização
 *   GET    /destinos/{id}         → Visualizar destino específico
 *   PATCH  /destinos/{id}/avaliar → Avaliar destino
 *   DELETE /destinos/{id}         → Excluir destino
 */
@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService destinoService;

    // Injeção de dependência via construtor (boa prática sobre @Autowired em campo)
    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    // ─── 1. POST /destinos ──────────────────────────────────────────────────
    // Cadastra um novo destino de viagem
    @PostMapping
    public ResponseEntity<Destino> cadastrar(@Valid @RequestBody DestinoRequestDTO dto) {
        Destino destino = destinoService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(destino);
    }

    // ─── 2. GET /destinos ───────────────────────────────────────────────────
    // Retorna a lista completa de destinos disponíveis
    @GetMapping
    public ResponseEntity<List<Destino>> listarTodos() {
        List<Destino> destinos = destinoService.listarTodos();
        return ResponseEntity.ok(destinos);
    }

    // ─── 3. GET /destinos/pesquisar?termo=xxx ───────────────────────────────
    // Pesquisa destinos por nome ou localização
    // Exemplo: GET /destinos/pesquisar?termo=Paris
    @GetMapping("/pesquisar")
    public ResponseEntity<List<Destino>> pesquisar(@RequestParam(required = false) String termo) {
        List<Destino> destinos = destinoService.pesquisar(termo);
        return ResponseEntity.ok(destinos);
    }

    // ─── 4. GET /destinos/{id} ──────────────────────────────────────────────
    // Retorna informações detalhadas de um destino específico
    @GetMapping("/{id}")
    public ResponseEntity<Destino> buscarPorId(@PathVariable Long id) {
        Destino destino = destinoService.buscarPorId(id);
        return ResponseEntity.ok(destino);
    }

    // ─── 5. PATCH /destinos/{id}/avaliar ────────────────────────────────────
    // Recebe uma nota de 1 a 10 e recalcula a média do destino
    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<Destino> avaliar(@PathVariable Long id,
                                           @Valid @RequestBody AvaliacaoRequestDTO dto) {
        Destino destino = destinoService.avaliar(id, dto);
        return ResponseEntity.ok(destino);
    }

    // ─── 6. DELETE /destinos/{id} ───────────────────────────────────────────
    // Exclui um destino de viagem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        destinoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

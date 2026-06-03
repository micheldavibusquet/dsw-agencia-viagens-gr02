package com.agencia.viagens.controller;

import com.agencia.viagens.dto.ReservaRequestDTO;
import com.agencia.viagens.model.Reserva;
import com.agencia.viagens.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para o recurso Reserva.
 *
 * Responsabilidade: receber requisições HTTP e delegar ao ReservaService.
 * Não contém lógica de negócio — apenas orquestra o fluxo da requisição.
 *
 * Endpoints disponíveis:
 *   POST /destinos/{id}/reservar   → Reservar pacote de viagem para um destino
 *   GET  /destinos/{id}/reservas   → Listar todas as reservas de um destino
 */
@RestController
@RequestMapping("/destinos")
public class ReservaController {

    /**
     * Injeção do ReservaService via construtor.
     * Boa prática: permite imutabilidade e facilita testes unitários com mocks.
     */
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // ─── POST /destinos/{id}/reservar ───────────────────────────────────────

    /**
     * Reserva um pacote de viagem para o destino informado.
     *
     * @param id  ID do destino a ser reservado
     * @param dto Dados da reserva (nome, e-mail, data, quantidade de pessoas)
     * @return    Reserva criada com valor total calculado — HTTP 201 Created
     *
     * Exemplo de requisição:
     * POST /destinos/1/reservar
     * {
     *   "nomeCliente": "Michel Busquet",
     *   "emailCliente": "michel@email.com",
     *   "dataViagem": "2025-12-20",
     *   "quantidadePessoas": 2
     * }
     */
    @PostMapping("/{id}/reservar")
    public ResponseEntity<Reserva> reservar(@PathVariable Long id,
                                            @Valid @RequestBody ReservaRequestDTO dto) {
        Reserva reserva = reservaService.reservar(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }

    // ─── GET /destinos/{id}/reservas ────────────────────────────────────────

    /**
     * Lista todas as reservas associadas a um destino específico.
     *
     * @param id ID do destino
     * @return   Lista de reservas — HTTP 200 OK
     *
     * Exemplo: GET /destinos/1/reservas
     */
    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<Reserva>> listarReservas(@PathVariable Long id) {
        List<Reserva> reservas = reservaService.listarPorDestino(id);
        return ResponseEntity.ok(reservas);
    }
}
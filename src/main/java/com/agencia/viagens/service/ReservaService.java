package com.agencia.viagens.service;

import com.agencia.viagens.dto.ReservaRequestDTO;
import com.agencia.viagens.model.Destino;
import com.agencia.viagens.model.Reserva;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Camada de serviço responsável pela lógica de negócio de Reservas.
 *
 * Responsabilidades:
 * - Validar se o destino existe antes de criar a reserva
 * - Calcular o valor total automaticamente (preço do destino × quantidade de pessoas)
 * - Armazenar reservas em memória (GR02)
 *   Na próxima etapa (GR03) será substituído pelo ReservaRepository com PostgreSQL
 */
@Service
public class ReservaService {

    /**
     * Injeção do DestinoService para validar se o destino existe
     * e buscar o preço para calcular o valor total da reserva.
     */
    private final DestinoService destinoService;

    /** Repositório em memória — será substituído pelo JPA no GR03 */
    private final List<Reserva> reservas = new ArrayList<>();

    /** Contador de IDs auto-incrementado */
    private final AtomicLong contadorId = new AtomicLong(1);

    /** Injeção de dependência via construtor (boa prática sobre @Autowired em campo) */
    public ReservaService(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    // ─── Reservar destino ────────────────────────────────────────────────────

    /**
     * Cria uma nova reserva para o destino informado.
     *
     * @param destinoId ID do destino a ser reservado
     * @param dto       Dados da reserva (cliente, data, quantidade de pessoas)
     * @return          Reserva criada com valor total calculado
     * @throws com.agencia.viagens.exception.DestinoNotFoundException se o destino não existir
     */
    public Reserva reservar(Long destinoId, ReservaRequestDTO dto) {

        // Valida se o destino existe — lança DestinoNotFoundException se não existir
        Destino destino = destinoService.buscarPorId(destinoId);

        // Monta a reserva com os dados recebidos
        Reserva reserva = new Reserva();
        reserva.setId(contadorId.getAndIncrement());
        reserva.setDestinoId(destinoId);
        reserva.setNomeCliente(dto.getNomeCliente());
        reserva.setEmailCliente(dto.getEmailCliente());
        reserva.setDataViagem(dto.getDataViagem());
        reserva.setQuantidadePessoas(dto.getQuantidadePessoas());

        // Calcula valor total: preço do destino × quantidade de pessoas
        double valorTotal = destino.getPreco() * dto.getQuantidadePessoas();
        reserva.setValorTotal(valorTotal);

        reservas.add(reserva);
        return reserva;
    }

    // ─── Listar reservas de um destino ──────────────────────────────────────

    /**
     * Retorna todas as reservas associadas a um destino específico.
     *
     * @param destinoId ID do destino
     * @return Lista de reservas do destino
     */
    public List<Reserva> listarPorDestino(Long destinoId) {
        return reservas.stream()
                .filter(r -> r.getDestinoId().equals(destinoId))
                .collect(Collectors.toList());
    }
}
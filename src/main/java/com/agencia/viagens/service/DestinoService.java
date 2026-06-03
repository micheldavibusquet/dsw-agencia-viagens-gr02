package com.agencia.viagens.service;

import com.agencia.viagens.dto.AvaliacaoRequestDTO;
import com.agencia.viagens.dto.DestinoRequestDTO;
import com.agencia.viagens.exception.DestinoNotFoundException;
import com.agencia.viagens.model.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Camada de serviço responsável pela lógica de negócio de Destinos.
 *
 * ⚠️ GR02: dados armazenados em memória (List).
 *    GR03: esta classe receberá injeção do DestinoRepository para persistir no PostgreSQL.
 *          Basta substituir a lista pelo repository — o controller não muda.
 */
@Service
public class DestinoService {

    // Simula o banco de dados em memória para o GR02
    // No GR03 será substituído pelo DestinoRepository (Spring Data JPA)
    private final List<Destino> repositorioEmMemoria = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    // ─── 1. Cadastrar destino ────────────────────────────────────────────────

    public Destino cadastrar(DestinoRequestDTO dto) {
        Destino destino = new Destino();
        destino.setId(contadorId.getAndIncrement());
        destino.setNome(dto.getNome());
        destino.setLocalizacao(dto.getLocalizacao());
        destino.setDescricao(dto.getDescricao());
        destino.setPreco(dto.getPreco());

        repositorioEmMemoria.add(destino);
        return destino;
    }

    // ─── 2. Listar todos os destinos ────────────────────────────────────────

    public List<Destino> listarTodos() {
        return new ArrayList<>(repositorioEmMemoria);
    }

    // ─── 3. Pesquisar por nome ou localização ───────────────────────────────

    public List<Destino> pesquisar(String termo) {
        if (termo == null || termo.isBlank()) {
            return listarTodos();
        }

        String termoBusca = termo.toLowerCase();

        return repositorioEmMemoria.stream()
                .filter(d -> d.getNome().toLowerCase().contains(termoBusca)
                          || d.getLocalizacao().toLowerCase().contains(termoBusca))
                .collect(Collectors.toList());
    }

    // ─── 4. Visualizar destino específico ───────────────────────────────────

    public Destino buscarPorId(Long id) {
        return repositorioEmMemoria.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DestinoNotFoundException(id));
    }

    // ─── 5. Avaliar destino ─────────────────────────────────────────────────

    public Destino avaliar(Long id, AvaliacaoRequestDTO dto) {
        Destino destino = buscarPorId(id);

        // Calcula nova média: (media_atual * qtd_avaliacoes + nova_nota) / (qtd_avaliacoes + 1)
        int novaQuantidade = destino.getQuantidadeAvaliacoes() + 1;
        double novaMedia = (destino.getNotaMedia() * destino.getQuantidadeAvaliacoes() + dto.getNota())
                / novaQuantidade;

        // Arredonda para 1 casa decimal
        destino.setNotaMedia(Math.round(novaMedia * 10.0) / 10.0);
        destino.setQuantidadeAvaliacoes(novaQuantidade);

        return destino;
    }

    // ─── 6. Excluir destino ─────────────────────────────────────────────────

    public void excluir(Long id) {
        Destino destino = buscarPorId(id);
        repositorioEmMemoria.remove(destino);
    }
}

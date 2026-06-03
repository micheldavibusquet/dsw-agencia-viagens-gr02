package com.agencia.viagens.model;

/**
 * Modelo que representa uma reserva de pacote de viagem.
 *
 * Uma Reserva está sempre associada a um Destino existente.
 * O valor total é calculado automaticamente pelo Service:
 * valorTotal = preço do destino × quantidade de pessoas.
 *
 * Status padrão ao criar: "CONFIRMADA"
 */
public class Reserva {

    /** Identificador único da reserva (gerado automaticamente) */
    private Long id;

    /** ID do destino de viagem associado a esta reserva */
    private Long destinoId;

    /** Nome completo do cliente que realizou a reserva */
    private String nomeCliente;

    /** E-mail do cliente para contato e confirmação */
    private String emailCliente;

    /** Data prevista para a viagem (formato: YYYY-MM-DD) */
    private String dataViagem;

    /** Quantidade de pessoas incluídas no pacote (mín: 1, máx: 20) */
    private Integer quantidadePessoas;

    /** Valor total da reserva: preço do destino × quantidade de pessoas */
    private Double valorTotal;

    /** Status atual da reserva: CONFIRMADA, CANCELADA ou PENDENTE */
    private String status;

    /**
     * Construtor padrão.
     * Define o status inicial como "CONFIRMADA".
     */
    public Reserva() {
        this.status = "CONFIRMADA";
    }

    // ─── Getters e Setters ───────────────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDestinoId() { return destinoId; }
    public void setDestinoId(Long destinoId) { this.destinoId = destinoId; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getEmailCliente() { return emailCliente; }
    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }

    public String getDataViagem() { return dataViagem; }
    public void setDataViagem(String dataViagem) { this.dataViagem = dataViagem; }

    public Integer getQuantidadePessoas() { return quantidadePessoas; }
    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
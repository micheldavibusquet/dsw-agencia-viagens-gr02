package com.agencia.viagens.dto;

import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para receber os dados de uma nova reserva.
 *
 * Responsável por validar os dados de entrada antes de chegarem ao Service.
 * Separa o contrato da API do modelo interno (Reserva),
 * evitando exposição desnecessária de campos internos.
 */
public class ReservaRequestDTO {

    /** Nome completo do cliente — campo obrigatório */
    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nomeCliente;

    /** E-mail válido do cliente — usado para confirmação da reserva */
    @NotBlank(message = "O e-mail do cliente é obrigatório")
    @Email(message = "E-mail inválido")
    private String emailCliente;

    /** Data prevista para a viagem no formato YYYY-MM-DD */
    @NotBlank(message = "A data da viagem é obrigatória")
    private String dataViagem;

    /** Quantidade de pessoas — mínimo 1, máximo 20 */
    @NotNull(message = "A quantidade de pessoas é obrigatória")
    @Min(value = 1, message = "A reserva deve ter ao menos 1 pessoa")
    @Max(value = 20, message = "A reserva suporta no máximo 20 pessoas")
    private Integer quantidadePessoas;

    // ─── Getters e Setters ───────────────────────────────────────────────────

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
}
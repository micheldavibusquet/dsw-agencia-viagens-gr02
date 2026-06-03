package com.agencia.viagens.dto;

import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para receber dados de criação/atualização de destino.
 * Separa o contrato da API do modelo interno — boa prática que facilita o GR03.
 */
public class DestinoRequestDTO {

    @NotBlank(message = "O nome do destino é obrigatório")
    private String nome;

    @NotBlank(message = "A localização é obrigatória")
    private String localizacao;

    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;

    // ─── Getters e Setters ───────────────────────────────────────────────────

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}

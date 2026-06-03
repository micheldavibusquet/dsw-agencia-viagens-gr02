package com.agencia.viagens.model;

/**
 * Modelo que representa um destino de viagem.
 * Na próxima etapa (GR03) esta classe receberá as anotações JPA
 * (@Entity, @Table, @Id, @GeneratedValue) para integração com o banco de dados.
 */
public class Destino {

    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private Double preco;
    private Double notaMedia;
    private Integer quantidadeAvaliacoes;

    // Construtor padrão
    public Destino() {
        this.notaMedia = 0.0;
        this.quantidadeAvaliacoes = 0;
    }

    // Construtor completo
    public Destino(Long id, String nome, String localizacao, String descricao, Double preco) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.preco = preco;
        this.notaMedia = 0.0;
        this.quantidadeAvaliacoes = 0;
    }

    // ─── Getters e Setters ───────────────────────────────────────────────────

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public Integer getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public void setQuantidadeAvaliacoes(Integer quantidadeAvaliacoes) {
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }
}

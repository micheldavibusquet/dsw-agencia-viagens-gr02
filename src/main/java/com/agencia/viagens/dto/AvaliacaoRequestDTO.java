package com.agencia.viagens.dto;

import jakarta.validation.constraints.*;

/**
 * DTO para receber a nota de avaliação de um destino.
 */
public class AvaliacaoRequestDTO {

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 10, message = "A nota máxima é 10")
    private Integer nota;

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}

package com.agencia.viagens.exception;

/**
 * Exceção lançada quando um destino não é encontrado.
 * Retorna HTTP 404 via GlobalExceptionHandler.
 */
public class DestinoNotFoundException extends RuntimeException {

    public DestinoNotFoundException(Long id) {
        super("Destino não encontrado com id: " + id);
    }
}

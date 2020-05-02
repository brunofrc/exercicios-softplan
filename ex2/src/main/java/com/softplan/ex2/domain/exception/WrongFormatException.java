package com.softplan.ex2.domain.exception;

/**
 * Exception usada para invalidar entrada com formato errado
 */
public class WrongFormatException extends RuntimeException {

    public WrongFormatException() {
        super("Formato de Json Inválido");
    }
}

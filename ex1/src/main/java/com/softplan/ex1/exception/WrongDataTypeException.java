package com.softplan.ex1.exception;

/**
 * Exception usada para invalidar tipos de entrada
 */
public class WrongDataTypeException extends RuntimeException {

    public WrongDataTypeException(String s) {
        super(s);
    }
}

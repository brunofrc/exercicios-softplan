package com.softplan.ex2.application;

/**
 * Interface de controle da aplicação,
 * responsável por fazer definir os métodos de comunicação entre a camada de interface do usário
 * com a camda de domiínio
 */
public interface IApplicationController {
    /**
     * Método responsável por receber uma lista json,
     * enviar para o texto para camada de domínio
     * e retornar uma string contendo o preço detalhado
     * das composições presentes na entrada json
     *
     * @param json
     * @return preço detalhado
     */
    String getPrecoDetalhadoComposicao(String json);
}

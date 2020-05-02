package com.softplan.ex2.domain.services.gerarPrecoDetalhado.interfaces;

/**
 * Interface do serviço de geração do preço detalhado das composiçōes,
 * onde estao definidos métodos expecíficos para esse tipo de serviço.
 */
public interface IGeraPrecoDetalhadoService {

    /**
     * Método responsável por receber uma lista json e retornar uma string
     * contendo o preço detalhado das composições presentes na entrada json
     *
     * @param json
     * @return preço detalhado
     */
    String geraPrecoDetalhadoComposicao(String json);
}

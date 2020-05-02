package com.softplan.ex2.domain.services.composicao.interfaces;

import com.softplan.ex2.domain.entities.Composicao;

/**
 * Interface do serviço de composiçōes,
 * onde estao definidos métodos expecíficos para esse tipo de serviço.
 *
 */
public interface IComposicaoService {


    /**
     * Metodo responsável por receber uma composiçaão e
     * retornar o cálculo do preco total
     * @param composicao
     * @return preco total
     */
    double calculaPrecoTotalComposicao(Composicao composicao);
}

package com.softplan.ex2.infra.interfaces;

import com.softplan.ex2.domain.entities.Composicao;

import java.util.List;

/**
 * Interface responsável pela definição dos métodos
 * de manipulação dos registros da classe {@link Composicao}
 */
public interface IComposicaoRepository {
    /**
     * Metodo responsável por adicionar uma composição no conjunto de registros
     * @param composicao
     */
    void addComposicao(Composicao composicao);

    /**
     * Metodo responsável por recuperar todas as composiçoes do conjunto de registros
     *
     */
    List<Composicao> findAll();

    /**
     * Método responsável por recuperar um registro de Composicao com base em seu códico
     * @param codigoComposicao
     * @return
     */
    Composicao findByCodigoComposicao(Integer codigoComposicao);

    /**
     * Método responsável por atualizar o registro de uma composição
     * @param composicao
     */
    void saveComposicao(Composicao composicao);

}

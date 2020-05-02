package com.softplan.ex2.domain.services.processarJson.interfaces;

import com.softplan.ex2.domain.entities.Composicao;

import java.util.List;

/**
 * Interface do serviço de processamento do json de composições,
 * onde estao definidos métodos expecíficos para esse tipo de servico.
 *
 */
public interface IProcessaJsonService {

    /**
     * Metodo responsavel por receber um json
     * e retornar todas as composicoes encontradas com seus items
     * @param json
     * @return lista de composiçoes
     */
    List<Composicao> processaJson(String json);
}

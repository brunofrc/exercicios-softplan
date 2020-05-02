package com.softplan.ex1.services.definicaoTexto.interfaces;

import java.util.List;

/**
 * Interface do serviço de definicao de texto,
 * onde estao definidos métodos expecíficos para esse tipo de servico.
 *
 */
public interface IDefinidorDeTexto {

    /**
     * Metodo responsavel por receber uma lista e retornar o texto correspondente a entrada
     * @param lista
     * @return texto corresponde
     */
    String retornaTextoCorrespondente(List lista);

}

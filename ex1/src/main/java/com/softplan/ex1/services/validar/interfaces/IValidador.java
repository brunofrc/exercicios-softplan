package com.softplan.ex1.services.validar.interfaces;

import java.util.List;
/**
 * Interface do serviço de validacao,
 * onde estao definidos métodos expecíficos para esse tipo de servico.
 *
 */
public interface IValidador {

    /**
     * Metodo responsavel por receber uma lista e verificar se ela esta valida
     * @param lista
     * @return lista válida ou inválida
     */
    boolean entradaValida(List lista);

}

package com.softplan.ex1.services.validar;

import com.softplan.ex1.services.validar.interfaces.IValidador;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de implementacao do serviço de validacao,
 * onde estao implementados métodos expecíficos para esse tipo de servico.
 *
 */
@Service
public class Validador implements IValidador {
    /*
     * (non-Javadoc)
     *
     * @see com.softplan.ex1.services.validar.interfaces.IValidador#entradaValida(java.util.List)
     */
    public boolean entradaValida(List lista) {
        return !lista.isEmpty();
    }
}

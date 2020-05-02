package com.softplan.ex1.services.definicaoTexto;

import com.softplan.ex1.services.definicaoTexto.interfaces.IDefinidorDeTexto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de implementacao do serviço de definicao de textos,
 * onde estao implementados métodos expecíficos para esse tipo de servico.
 *
 */
@Service
public class DefinidorDeTexto implements IDefinidorDeTexto {

    //Textos pré-definidos
    private static final String umoNota = "Fatura da nota fiscal de simples remessa: ";
    private static final String pluralText = "Fatura das notas fiscais de simples remessa: ";

    /*
     * (non-Javadoc)
     *
     * @see com.softplan.ex1.services.definicaoTexto.interfaces.IDefinidorDeTexto#retornaTextoCorrespondente(java.util.List)
     */
    @Override
    public String retornaTextoCorrespondente(List lista) {
        if (lista.size() >= 2) {
            return pluralText;
        } else {
            return umoNota;
        }
    }
}

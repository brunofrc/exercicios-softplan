package com.softplan.ex1.services.gerarObservacao;

import com.softplan.ex1.services.definicaoTexto.interfaces.IDefinidorDeTexto;
import com.softplan.ex1.services.validar.interfaces.IValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe abstrata do serviço de geração de observacao,
 * onde estao implementados métodos universais
 * e declarados métodos abstratos para esse tipo de servico.
 *
 */
@Component
public abstract class GenericGeradorObservacao {

    @Autowired
    private IDefinidorDeTexto definidorDeTexto;

    @Autowired
    private IValidador validador;


    //Gera observaçoeses, com texto pre-definido, incluindo os números, das notas fiscais, recebidos no parâmetro
    public String geraObservacao(List lista) {
        if (validador.entradaValida(lista)) {
            return retornaCodigos(lista) + ".";
        }
        return "";
    }

    //Cria observa��o
    private String retornaCodigos(List lista) {

        return definidorDeTexto.retornaTextoCorrespondente(lista) + montaSeparador(lista);
    }

    //Metodo abstrato para montar os separadores,
    //cada subClasse implementa esse método com base em suas regras de negócio específicas
    protected abstract String montaSeparador(List lista);


}

package com.softplan.ex2.application;

import com.softplan.ex2.domain.services.gerarPrecoDetalhado.interfaces.IGeraPrecoDetalhadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Classe de controle da aplicação,
 * responsável por implementar os métodos de comunicação entre a camada de interface do usário
 * e a camda de domínio
 */
@Component
@ComponentScan("com.softplan.ex2.domain")
public class ApplicationController implements IApplicationController {

    @Autowired
    IGeraPrecoDetalhadoService getPrecoDetalhadoComposicao;

    public String getPrecoDetalhadoComposicao(String json) {
        return getPrecoDetalhadoComposicao.geraPrecoDetalhadoComposicao(json);
    }
}

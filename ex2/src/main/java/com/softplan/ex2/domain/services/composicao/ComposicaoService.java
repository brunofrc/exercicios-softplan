package com.softplan.ex2.domain.services.composicao;

import com.softplan.ex2.domain.entities.Composicao;
import com.softplan.ex2.domain.entities.ItemComposicao;
import com.softplan.ex2.domain.services.composicao.interfaces.IComposicaoService;
import com.softplan.ex2.infra.interfaces.IComposicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Classe de implementacao do serviço de composicoes,
 * onde estao implementados métodos expecíficos para esse tipo de servico.
 */
@Component
@ComponentScan("com.softplan.ex2.infra")
public class ComposicaoService implements IComposicaoService {

    @Autowired
    private IComposicaoRepository composicaoRepository;

    /*
     * (non-Javadoc)
     *
     * @see(com.softplan.ex2.domain.services.composicao.IComposicaoService#calculaPrecoTotalComposicao(com.softplan.ex2.domain.entities.Composicao)
     */
    public double calculaPrecoTotalComposicao(Composicao composicao) {
        double total = 0;
        for (ItemComposicao itemComposicao : composicao.getItemComposicaoList()) {
            if (itemComposicao.isComposicao()) {
                //caso o item seja uma composicao, recupera a composicao, calcula o seu preço total
                //e multiplica pela quantidade do item para termos o preco desse item
                Composicao item = composicaoRepository.findByCodigoComposicao(itemComposicao.getCodigoItem());
                total += itemComposicao.getQuantidadeComposicao() * calculaPrecoTotalComposicao(item);
            } else {
                total += itemComposicao.getQuantidadeComposicao() * itemComposicao.getValorUnitario();
            }
        }
        return total;
    }
}

package com.softplan.ex2.domain.services.gerarPrecoDetalhado;

import com.softplan.ex2.domain.entities.Composicao;
import com.softplan.ex2.domain.services.composicao.interfaces.IComposicaoService;
import com.softplan.ex2.domain.services.gerarPrecoDetalhado.interfaces.IGeraPrecoDetalhadoService;
import com.softplan.ex2.domain.services.processarJson.interfaces.IProcessaJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Classe de implementacao do serviço de composicoes,
 * onde estao implementados métodos expecíficos para esse tipo de servico.
 */
@Service
public class GeraPrecoDetalhadoService implements IGeraPrecoDetalhadoService {

    @Autowired
    private IProcessaJsonService processaJsonService;

    @Autowired
    private IComposicaoService composicaoService;

    /**
     * Método responsável por receber uma lista json e retornar uma string
     * contendo o preço detalhado das composições presentes na entrada json
     *
     * @param json
     * @return preço detalhado
     */
    public String geraPrecoDetalhadoComposicao(String json) {
        List<Composicao> composicaoList = processaJsonService.processaJson(json);

        //utilizado para o separador de decimal sair com virgula e não um ponto
        Locale.setDefault(new Locale("pt", "BR"));
        //Formatar o número conforme o desejado
        DecimalFormat formatter = new DecimalFormat("0.00");

        StringBuilder detalhePrecoComposicao = new StringBuilder();
        for (Composicao composicao : composicaoList) {
            String precoFormatado = formatter.format(composicaoService.calculaPrecoTotalComposicao(composicao));
            detalhePrecoComposicao.append(composicao.getCodigoComposicao()).
                    append(" ").
                    append(composicao.getDescricaoComposicao()).
                    append(" ").
                    append(composicao.getUnidadeComposicao()).
                    append(" ").
                    append(precoFormatado).
                    append("\n");
        }
        return detalhePrecoComposicao.toString();
    }
}

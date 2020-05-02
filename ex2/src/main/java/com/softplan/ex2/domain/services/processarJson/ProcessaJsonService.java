package com.softplan.ex2.domain.services.processarJson;

import com.softplan.ex2.domain.entities.Composicao;
import com.softplan.ex2.domain.entities.ItemComposicao;
import com.softplan.ex2.domain.exception.WrongFormatException;
import com.softplan.ex2.domain.services.processarJson.interfaces.IProcessaJsonService;
import com.softplan.ex2.infra.interfaces.IComposicaoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Classe de implementacao do serviço de processamento do json de composições,
 * onde estao implementados métodos expecíficos para esse tipo de servico.
 */
@Service
@ComponentScan("com.softplan.ex2.infra")
public class ProcessaJsonService implements IProcessaJsonService {

    @Autowired
    private IComposicaoRepository composicaoRepository;

    /*
     * (non-Javadoc)
     *
     * @see(com.softplan.ex2.domain.services.processaJson.IProcessaJsonService#processaJson(com.softplan.ex2.domain.entities.Composicao)
     */
    public List<Composicao> processaJson(String json) {
        JSONArray jsonArray = new JSONArray(json);

        //Utilizado para converter um numero com virgula em um double
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

        try {
            for (Object item : jsonArray) {
                JSONObject jsonObject = (JSONObject) item;

                //mapeia os atributos do item
                Integer codigoComposicao = (Integer) jsonObject.get("codigoComposicao");
                String descricaoComposicao = (String) jsonObject.get("descricaoComposicao");
                String unidadeComposicao = (String) jsonObject.get("unidadeComposicao");
                Integer codigoItem = (Integer) jsonObject.get("codigoItem");
                String tipoItem = (String) jsonObject.get("tipoItem");
                String descricaoItemComposicao = (String) jsonObject.get("descricaoItemComposicao");
                String unidadeItem = (String) jsonObject.get("unidadeItem");
                double quantidadeComposicao = format.parse((String) jsonObject.get("quantidadeComposicao")).doubleValue();
                double valorUnitario = 0;

                if (tipoItem.equals("COMPOSICAO")) {
                    //caso o item seja uma composicao e ainda nao há registro dessa composicao,
                    //adiciona um novo registro com as informacoes do item
                    Composicao itemComposicao = composicaoRepository.findByCodigoComposicao(codigoComposicao);
                    if (itemComposicao == null) {
                        itemComposicao = new Composicao(codigoItem, descricaoItemComposicao, unidadeItem);
                        composicaoRepository.addComposicao(itemComposicao);
                    }

                } else {
                    //Caso o item seja um insumo ele possui valorUnitario
                    valorUnitario = format.parse((String) jsonObject.get("valorUnitario")).doubleValue();

                }
                //cria um registro para o item
                ItemComposicao itemComposicao = new ItemComposicao(codigoItem, tipoItem, quantidadeComposicao, valorUnitario);

                //Caso ainda nao tenha um registro da composicao do item,
                //adiciona um novo registro
                Composicao composicao = composicaoRepository.findByCodigoComposicao(codigoComposicao);
                if (composicaoRepository.findByCodigoComposicao(codigoComposicao) == null) {
                    composicao = new Composicao(codigoComposicao, descricaoComposicao, unidadeComposicao);
                    composicaoRepository.addComposicao(composicao);
                }
                //Adiciona o item na composicao
                composicao.getItemComposicaoList().add(itemComposicao);
                //Atualiza o registro da composicao
                composicaoRepository.saveComposicao(composicao);
            }
        } catch (Exception ex) {
            throw new WrongFormatException();
        }
        //retorna todas as composicoes encontradas no json
        return composicaoRepository.findAll();

    }
}

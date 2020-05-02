package com.softplan.ex2.infra;

import com.softplan.ex2.domain.entities.Composicao;
import com.softplan.ex2.infra.interfaces.IComposicaoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Classe responsável pela implementação dos métodos
 * de manipulação dos registros da classe {@link Composicao}
 */
@Repository
public class ComposicaoRepository implements IComposicaoRepository {

    //Set responsável por guardar os registros de Composiçoes
    private Set<Composicao> composicaoSet;

    /*
     * (non-Javadoc)
     *
     * @see(com.softplan.ex2.domain.services.processaJson.IProcessaJsonService#processaJson(com.softplan.ex2.domain.entities.Composicao)
     */
    public void addComposicao(Composicao composicao){
        if(composicaoSet == null){
            composicaoSet = new HashSet<>();
        }
        composicaoSet.add(composicao);
    }

    /*
     * (non-Javadoc)
     *
     * @see(com.softplan.ex2.domain.services.processaJson.IProcessaJsonService#processaJson(com.softplan.ex2.domain.entities.Composicao)
     */
    public List<Composicao> findAll(){
        return new ArrayList<>(composicaoSet);
    }

    /*
     * (non-Javadoc)
     *
     * @see(com.softplan.ex2.domain.services.processaJson.IProcessaJsonService#processaJson(com.softplan.ex2.domain.entities.Composicao)
     */
    public Composicao findByCodigoComposicao(Integer codigoComposicao){
        if(composicaoSet == null){
            return null;
        }
        return composicaoSet.stream().filter(composicao -> composicao.getCodigoComposicao().equals(codigoComposicao)).findFirst().orElse(null);
    }

    /*
     * (non-Javadoc)
     *
     * @see(com.softplan.ex2.domain.services.processaJson.IProcessaJsonService#processaJson(com.softplan.ex2.domain.entities.Composicao)
     */
    public void saveComposicao(Composicao composicao){
        Composicao oldComposicao = findByCodigoComposicao(composicao.getCodigoComposicao());
        composicaoSet.remove(oldComposicao);
        addComposicao(composicao);
    }
}

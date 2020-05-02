package com.softplan.ex2.domain.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por todos os atributos do objeto
 *  * {@link Composicao}.
 */
public class Composicao {

    private final Integer codigoComposicao;

    private final String descricaoComposicao;

    private final String unidadeComposicao;

    private final List<ItemComposicao> itemComposicaoList;

    /**
     * Construtor
     * @param codigoComposicao
     * @param descricaoComposicao
     * @param unidadeComposicao
     */
    public Composicao(Integer codigoComposicao, String descricaoComposicao, String unidadeComposicao) {
        this.codigoComposicao = codigoComposicao;
        this.descricaoComposicao = descricaoComposicao;
        this.unidadeComposicao = unidadeComposicao;
        this.itemComposicaoList = new ArrayList<>();
    }

    public String getDescricaoComposicao() {
        return descricaoComposicao;
    }

    public String getUnidadeComposicao() {
        return unidadeComposicao;
    }

    public Integer getCodigoComposicao() {
        return codigoComposicao;
    }

    public List<ItemComposicao> getItemComposicaoList() {
        return itemComposicaoList;
    }
}
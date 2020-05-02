package com.softplan.ex2.domain.entities;

/**
 * Classe responsável por todos os atributos do ItemComposicao
 *  * {@link Composicao}.
 */
public class ItemComposicao {

    private final String tipoItem;

    private final Integer codigoItem;

    private final double quantidadeComposicao;

    private final double valorUnitario;

    public ItemComposicao(Integer codigoItem, String tipoItem, double quantidadeComposicao, double valorUnitario) {
        this.tipoItem = tipoItem;
        this.codigoItem = codigoItem;
        this.quantidadeComposicao = quantidadeComposicao;
        this.valorUnitario = valorUnitario;
    }

    /**
     * Método responsável por verificar se o item é do tipo COMPOSICAO
     * @return verdadeiro se o tipoItem for COMPOSICAO
     */
    public boolean isComposicao(){
        return tipoItem.equals("COMPOSICAO");
    }

    public Integer getCodigoItem() {
        return codigoItem;
    }

    public double getQuantidadeComposicao() {
        return quantidadeComposicao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }
}

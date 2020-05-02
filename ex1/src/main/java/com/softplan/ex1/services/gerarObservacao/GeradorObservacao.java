package com.softplan.ex1.services.gerarObservacao;

import com.softplan.ex1.exception.WrongDataTypeException;

import java.util.Iterator;
import java.util.List;

/**
 * Classe concreta do serviço de geração de observaçäo de remessas simples com números,
 * onde estao implementados métodos expecíficos para esse tipo de servico.
 *
 */
public class GeradorObservacao extends GenericGeradorObservacao {


    /**
     * Método responsável por separar os números da lista de entrada e retornar
     * um texto contendo os numeros com separadores
     * @param lista
     * @return separador de valores
     */
    @Override
    public String montaSeparador(List lista) {
        //Acha separador
        StringBuilder cod = new StringBuilder();
        for (Iterator<Integer> iterator = lista.iterator(); iterator.hasNext(); ) {
            try{
                Integer c = iterator.next() ;
                String s;
                if (cod.toString().length() <= 0)
                    s = "";
                else if (iterator.hasNext())
                    s = ", ";
                else
                    s = " e ";

                cod.append(s + c);
            } catch (ClassCastException ex) {
                throw new WrongDataTypeException("Tipo de entrada inválida! está é uma geração de observação simples e aceita apenas uma lista de numeros de remessas");
            }
        }
        return cod.toString();
    }
}
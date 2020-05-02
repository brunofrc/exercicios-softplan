package com.softplan.ex1.services.gerarObservacao;

import com.softplan.ex1.exception.WrongDataTypeException;
import com.softplan.ex1.valueObjects.Remessa;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Classe concreta do serviço de geração de observacao de remessas com números e valores,
 * onde estao implementados métodos expecíficos esse tipo de servico.
 *
 */
public class GeradorObservacaoComValor extends GenericGeradorObservacao {

    /**
     * Método responsável por separar as Remessas da lista de entrada e retornar
     * um texto contendo os numeros e valores de cada remessa
     * @param lista
     * @return separador de valores
     */
    @Override
    public String montaSeparador(List lista) {
        //Acha separador
        StringBuilder cod = new StringBuilder();

        //utilizado para o separador de decimal sair com virgula e não um ponto
        Locale.setDefault(new Locale("pt", "BR"));
        //Formatar o número conforme o desejado
        DecimalFormat df = new DecimalFormat("#,##0.00");

        double total = 0;
        for (Iterator<Remessa> iterator = lista.iterator(); iterator.hasNext(); ) {
            try {
                Remessa remessa = iterator.next();
                String c = remessa.getNumero() + " cujo valor é R$ " + df.format(remessa.getValor());
                String s;
                if (cod.toString().length() <= 0)
                    s = "";
                else if (iterator.hasNext())
                    s = ", ";
                else
                    s = " e ";

                cod.append(s + c);
                total += remessa.getValor();
            } catch (ClassCastException ex) {
                throw new WrongDataTypeException("Tipo de entrada inválida! está é um geração de observação com valores e aceita apenas uma lista de remessas com numero e valor");
            }
        }
        cod.append(". Total = " + df.format(total));
        return cod.toString();
    }
}

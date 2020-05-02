package com.softplan.ex1.Services;

import com.softplan.ex1.services.definicaoTexto.DefinidorDeTexto;
import com.softplan.ex1.services.definicaoTexto.interfaces.IDefinidorDeTexto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe de Teste responsavel por garantir a cobertura de testes, referente aos
 * metodos presentes na classe {@link DefinidorDeTexto}.
 */
@SpringBootTest
public class DefinidorDeTextoTest {

    @Spy
    private IDefinidorDeTexto definidorDeTexto = new DefinidorDeTexto();

    @Test
    public void processaTextoListSize1() {
        //Prepara dados de entrada
        List entrada = Collections.singletonList(1);

        //Chama metodo a ser testado
        String result = definidorDeTexto.retornaTextoCorrespondente(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertEquals("Fatura da nota fiscal de simples remessa: ", result);
    }

    @Test
    public void processaTextoListSizeGreaterThan2() {
        //Prepara dados de entrada
        List entrada = Arrays.asList(1, 2);

        //Chama metodo a ser testado
        String result = definidorDeTexto.retornaTextoCorrespondente(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertEquals("Fatura das notas fiscais de simples remessa: ", result);
    }
}

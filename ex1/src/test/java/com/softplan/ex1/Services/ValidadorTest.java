package com.softplan.ex1.Services;

import com.softplan.ex1.services.validar.Validador;
import com.softplan.ex1.services.validar.interfaces.IValidador;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe de Teste responsavel por garantir a cobertura de testes, referente aos
 * metodos presentes na classe {@link Validador}.
 */
@SpringBootTest
public class ValidadorTest {

    @Spy
    private IValidador validador = new Validador();

    @Test
    public void entradaNaoValidaListaVazia() {
        //Prepara dados de entrada
        List entrada = Collections.emptyList();

        //Chama metodo a ser testado
        boolean result = validador.entradaValida(entrada);

        //valida resultado
        Assert.assertFalse(result);
    }

    @Test
    public void entradaValidaLIstaNaoVazia() {
        //Prepara dados de entrada
        List entrada = Arrays.asList(1, 2);

        //Chama metodo a ser testado
        boolean result = validador.entradaValida(entrada);

        //valida resultado
        Assert.assertTrue(result);
    }
}

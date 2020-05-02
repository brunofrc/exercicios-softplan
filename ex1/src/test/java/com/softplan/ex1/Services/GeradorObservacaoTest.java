package com.softplan.ex1.Services;

import com.softplan.ex1.exception.WrongDataTypeException;
import com.softplan.ex1.services.definicaoTexto.interfaces.IDefinidorDeTexto;
import com.softplan.ex1.services.gerarObservacao.GenericGeradorObservacao;
import com.softplan.ex1.services.gerarObservacao.GeradorObservacao;
import com.softplan.ex1.services.validar.interfaces.IValidador;
import com.softplan.ex1.valueObjects.Remessa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe de Teste responsavel por garantir a cobertura de testes, referente aos
 * metodos presentes na classe {@link GeradorObservacao}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GeradorObservacaoTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private IDefinidorDeTexto definidorDeTexto;

    @Mock
    private IValidador validador;

    @Spy
    @InjectMocks
    private GenericGeradorObservacao geradorObservacao = new GeradorObservacao();

    @Before
    public void initCommonMocks() {
        Mockito.when(validador.entradaValida(Mockito.argThat(list -> !list.isEmpty()))).thenReturn(true);
    }


    @Test
    public void mantemFuncionalidadeListaVaziaTest() {

        //Prepara dados de entrada
        List entrada = Collections.emptyList();

        //Chama metodo a ser testado
        String result = geradorObservacao.geraObservacao(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());

    }

    @Test
    public void mantemFuncionalidadeListaNaoVaziaTest() {

        //Prepara dados de entrada
        List entrada = Arrays.asList(1, 2, 3, 4, 5);

        //Prepara Mocks
        Mockito.when(definidorDeTexto.retornaTextoCorrespondente(Mockito.argThat(list -> list.size() > 2))).thenReturn("Fatura das notas fiscais de simples remessa: ");

        //Chama metodo a ser testado
        String result = geradorObservacao.geraObservacao(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertEquals("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5.", result);

    }

    @Test
    public void mantemFuncionalidadeListaNaoVaziaSize1Test() {

        //Prepara dados de entrada
        List entrada = Collections.singletonList(1);

        //Prepara Mocks
        Mockito.when(definidorDeTexto.retornaTextoCorrespondente(Mockito.argThat(list -> list.size() < 2))).thenReturn("Fatura da nota fiscal de simples remessa: ");

        //Chama metodo a ser testado
        String result = geradorObservacao.geraObservacao(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertEquals("Fatura da nota fiscal de simples remessa: 1.", result);

    }

    @Test
    public void testaEntradaComTipoInvalidoTest1() {

        //declara exception esperada e mensagem de retorno
        exception.expect(WrongDataTypeException.class);
        exception.expectMessage("Tipo de entrada inválida! está é uma geração de observação simples e aceita apenas uma lista de numeros de remessas");

        //Prepara dados de entrada
        List entrada = Collections.singletonList(new Remessa(1, 0));

        //Chama metodo a ser testado
        geradorObservacao.geraObservacao(entrada);

    }

    @Test
    public void testaEntradaComTipoInvalidoTest2() {

        //declara exception esperada e mensagem de retorno
        exception.expect(WrongDataTypeException.class);
        exception.expectMessage("Tipo de entrada inválida! está é uma geração de observação simples e aceita apenas uma lista de numeros de remessas");

        //Prepara dados de entrada
        List entrada = Arrays.asList(1, 2, 3, new Remessa(4, 0));

        //Chama metodo a ser testado
        geradorObservacao.geraObservacao(entrada);

    }

}

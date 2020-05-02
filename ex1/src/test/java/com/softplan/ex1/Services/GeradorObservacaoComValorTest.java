package com.softplan.ex1.Services;

import com.softplan.ex1.exception.WrongDataTypeException;
import com.softplan.ex1.services.definicaoTexto.interfaces.IDefinidorDeTexto;
import com.softplan.ex1.services.gerarObservacao.GenericGeradorObservacao;
import com.softplan.ex1.services.gerarObservacao.GeradorObservacaoComValor;
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
 * metodos presentes na classe {@link GeradorObservacaoComValor}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GeradorObservacaoComValorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private IDefinidorDeTexto definidorDeTexto;

    @Mock
    private IValidador validador;

    @Spy
    @InjectMocks
    private GenericGeradorObservacao geradorObservacao = new GeradorObservacaoComValor();

    @Before
    public void initCommonMocks() {
        Mockito.when(validador.entradaValida(Mockito.argThat(list -> !list.isEmpty()))).thenReturn(true);
    }


    @Test
    public void novaFuncionalidadeListaVaziaTest() {

        //Prepara dados de entrada
        List entrada = Collections.emptyList();

        //Chama metodo a ser testado
        String result = geradorObservacao.geraObservacao(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());

    }

    @Test
    public void novaFuncionalidadeListaNaoVaziaTest() {

        //Prepara dados de entrada
        List entrada = Arrays.asList(new Remessa(1, 10.00), new Remessa(2, 35.00), new Remessa(3, 5.00), new Remessa(4, 1500.00), new Remessa(5, 0.30));

        //Prepara Mocks
        Mockito.when(definidorDeTexto.retornaTextoCorrespondente(Mockito.argThat(list -> list.size() > 2))).thenReturn("Fatura das notas fiscais de simples remessa: ");

        //Chama metodo a ser testado
        String result = geradorObservacao.geraObservacao(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertEquals("Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total = 1.550,30.", result);

    }

    @Test
    public void novaFuncionalidadeListaNaoVaziaSize1Test() {

        //Prepara dados de entrada
        List entrada = Collections.singletonList(new Remessa(1, 10.00));

        //Prepara Mocks
        Mockito.when(definidorDeTexto.retornaTextoCorrespondente(Mockito.argThat(list -> list.size() < 2))).thenReturn("Fatura da nota fiscal de simples remessa: ");

        //Chama metodo a ser testado
        String result = geradorObservacao.geraObservacao(entrada);

        //valida resultado
        Assert.assertNotNull(result);
        Assert.assertEquals("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00. Total = 10,00.", result);

    }

    @Test
    public void testaEntradaComTipoInvalidoTest1() {

        //declara exception esperada e mensagem de retorno
        exception.expect(WrongDataTypeException.class);
        exception.expectMessage("Tipo de entrada inválida! está é um geração de observação com valores e aceita apenas uma lista de remessas com numero e valor");

        //Prepara dados de entrada
        List entrada = Collections.singletonList(1);

        //Chama metodo a ser testado
        geradorObservacao.geraObservacao(entrada);

    }

    @Test
    public void testaEntradaComTipoInvalidoTest2() {

        //declara exception esperada e mensagem de retorno
        exception.expect(WrongDataTypeException.class);
        exception.expectMessage("Tipo de entrada inválida! está é um geração de observação com valores e aceita apenas uma lista de remessas com numero e valor");

        //Prepara dados de entrada
        List entrada = Arrays.asList(new Remessa(2, 10.00), 2, 3);

        //Chama metodo a ser testado
        geradorObservacao.geraObservacao(entrada);

    }

}

package com.softplan.ex2.composicao;


import com.softplan.ex2.domain.entities.Composicao;
import com.softplan.ex2.domain.entities.ItemComposicao;
import com.softplan.ex2.domain.services.composicao.ComposicaoService;
import com.softplan.ex2.domain.services.composicao.interfaces.IComposicaoService;
import com.softplan.ex2.infra.interfaces.IComposicaoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * Classe de Teste responsavel por garantir a cobertura de testes, referente aos
 * metodos presentes na classe {@link com.softplan.ex2.domain.services.composicao.ComposicaoService}.
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ComposicaoServiceTest {

    @Mock
    private IComposicaoRepository composicaoRepository;

    @Spy
    @InjectMocks
    private IComposicaoService composicaoService = new ComposicaoService();

    @Test
    public void calculaPrecoTotalComposicaoComInsumosTest(){
        //Prepara dados para teste
        Composicao composicao = new Composicao(1, "COMPOSICAO TESTE", "TESTE");
        composicao.getItemComposicaoList().
                addAll(
                        Arrays.asList(
                                new ItemComposicao(1, "INSUMO", 0.73, 4.44),
                                new ItemComposicao(2, "INSUMO", 0.25, 5.66)));
        //Chama metodo a ser testado
        double result = composicaoService.calculaPrecoTotalComposicao(composicao);

        //valida resultado
        Assert.assertEquals(4.6562, result, 0);

    }

    @Test
    public void calculaPrecoTotalComposicaoComInsumosEComposicoesTest(){
        //Prepara dados para teste
        Composicao composicao = new Composicao(1, "COMPOSICAO TESTE", "TESTE");
        composicao.getItemComposicaoList().
                addAll(
                        Arrays.asList(
                                new ItemComposicao(1, "INSUMO", 0.73, 4.44),
                                new ItemComposicao(2, "COMPOSICAO", 0.5, 0)));
        Composicao composicao2 = new Composicao(2, "COMPOSICAO TESTE", "TESTE");
        composicao2.getItemComposicaoList().
                add(
                        new ItemComposicao(1, "INSUMO", 1, 4.44));

        //prepara mock
        Mockito.when(composicaoRepository.findByCodigoComposicao(2)).thenReturn(composicao2);

        //Chama metodo a ser testado
        double result = composicaoService.calculaPrecoTotalComposicao(composicao);

        //valida resultado
        Assert.assertEquals(5.4612, result, 0);

    }


}

package com.softplan.ex2.gerarPrecoDetalhado;

import com.softplan.ex2.domain.entities.Composicao;
import com.softplan.ex2.domain.entities.ItemComposicao;
import com.softplan.ex2.domain.services.composicao.interfaces.IComposicaoService;
import com.softplan.ex2.domain.services.gerarPrecoDetalhado.GeraPrecoDetalhadoService;
import com.softplan.ex2.domain.services.gerarPrecoDetalhado.interfaces.IGeraPrecoDetalhadoService;
import com.softplan.ex2.domain.services.processarJson.interfaces.IProcessaJsonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * Classe de Teste responsavel por garantir a cobertura de testes, referente aos
 * metodos presentes na classe {@link com.softplan.ex2.domain.services.gerarPrecoDetalhado.GeraPrecoDetalhadoService}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GerarPrecoDetalhadoTest {
    @Mock
    private IComposicaoService composicaoService;

    @Mock
    private IProcessaJsonService processaJsonService;

    @Spy
    @InjectMocks
    private IGeraPrecoDetalhadoService geraPrecoDetalhadoService = new GeraPrecoDetalhadoService();

    @Test
    public void geraPrecoDetalhadoComposicaoTest(){
        //Prepara dados
        Composicao composicao = new Composicao(98561,"IMPERMEABILIZAÇÃO DE PAREDES COM ARGAMASSA DE CIMENTO E AREIA, COM ADITIVO IMPERMEABILIZANTE, E = 2CM. AF_06/2018", "M2");
        composicao.getItemComposicaoList().
                    add(
                        new ItemComposicao(7325, "INSUMO", 0.387, 4.44));

        //Prepara Mock
        Mockito.when(processaJsonService.processaJson(Mockito.anyString())).
                thenReturn(Collections.singletonList(composicao));

        Mockito.when(composicaoService.calculaPrecoTotalComposicao(composicao)).
                thenReturn(1.71828);

        //chama método a ser testado
        String result = geraPrecoDetalhadoService.geraPrecoDetalhadoComposicao("");

        //verifica resultado
        Assert.assertNotNull(result);
        Assert.assertEquals("98561 IMPERMEABILIZAÇÃO DE PAREDES COM ARGAMASSA DE CIMENTO E AREIA, COM ADITIVO IMPERMEABILIZANTE, E = 2CM. AF_06/2018 M2 1,72\n", result);
    }
}

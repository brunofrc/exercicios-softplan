package com.softplan.ex2.processarJson;

import com.softplan.ex2.domain.entities.Composicao;
import com.softplan.ex2.domain.entities.ItemComposicao;
import com.softplan.ex2.domain.exception.WrongFormatException;
import com.softplan.ex2.domain.services.processarJson.ProcessaJsonService;
import com.softplan.ex2.domain.services.processarJson.interfaces.IProcessaJsonService;
import com.softplan.ex2.infra.interfaces.IComposicaoRepository;
import org.junit.Assert;
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
import java.util.List;

/**
 * Classe de Teste responsavel por garantir a cobertura de testes, referente aos
 * metodos presentes na classe {@link com.softplan.ex2.domain.services.processarJson.ProcessaJsonService}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProcessaJsonServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private IComposicaoRepository composicaoRepository;

    @Spy
    @InjectMocks
    private IProcessaJsonService processaJsonService = new ProcessaJsonService();

    @Test
    public void processaJsonTest() {
        //Prepara dados
        String json = getJsonParaTeste();
        Composicao composicao = new Composicao(94793, "REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016", "UN");
        Composicao composicao2 = new Composicao(87286, "REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016", "UN");
        composicao.getItemComposicaoList().
                addAll(
                        Arrays.asList(
                                new ItemComposicao(88248, "INSUMO", 0.7890000, 15.19),
                                new ItemComposicao(87286, "COMPOSICAO", 0.250000, 0)));

        //Prepara mock
        Mockito.when(composicaoRepository.findByCodigoComposicao(94793)).thenReturn(composicao);
        Mockito.doNothing().when(composicaoRepository).saveComposicao(composicao);
        Mockito.when(composicaoRepository.findAll()).thenReturn(Arrays.asList(composicao, composicao2));

        //chama método a ser testado
        List<Composicao> composicaoList = processaJsonService.processaJson(json);

        Assert.assertEquals(Arrays.asList(composicao, composicao2), composicaoList);

    }

    @Test
    public void processaJsonWrongFormatExceptionTest() {
        //Prepara dados
        String wrongJson = "[" +
                "{\"codigoComposicao\": 94.793," +
                "\"codigoItem\": \"CODIGO ITEM\"," +
                "\"valorUnitario\": 1519" +
                "}]";
        //declara exception esperada e mensagem de retorno
        exception.expect(WrongFormatException.class);
        exception.expectMessage("Formato de Json Inválido");

        //chama método a ser testado
        processaJsonService.processaJson(wrongJson);

    }

    private String getJsonParaTeste() {
        return "[" +
                "{\"codigoComposicao\": 94793," +
                "\"descricaoComposicao\": \"REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016\"," +
                "\"unidadeComposicao\": \"UN\"," +
                "\"tipoItem\": \"INSUMO\"," +
                "\"codigoItem\": 88248," +
                "\"descricaoItemComposicao\": \"AUXILIAR DE ENCANADOR OU BOMBEIRO HIDRÁULICO COM ENCARGOS COMPLEMENTARES\"," +
                "\"unidadeItem\": \"H\"," +
                "\"quantidadeComposicao\": \"0,7890000\"," +
                "\"valorUnitario\": \"15,19\"" +
                "}," +
                "{" +
                "\"codigoComposicao\": 94793," +
                "\"descricaoComposicao\": \"REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016\"," +
                "\"unidadeComposicao\": \"UN\"," +
                "\"tipoItem\": \"COMPOSICAO\"," +
                "\"codigoItem\": 87286," +
                "\"descricaoItemComposicao\": \"ARGAMASSA TRAÇO 1:1:6 (CIMENTO, CAL E AREIA MÉDIA) PARA EMBOÇO/MASSA ÚNICA/ASSENTAMENTO DE ALVENARIA DE VEDAÇÃO, PREPARO MECÂNICO COM BETONEIRA 400 L. AF_06/2014\"," +
                "\"unidadeItem\": \"M3\"," +
                "\"quantidadeComposicao\": \"0,0250000\"," +
                "\"valorUnitario\": \"\"" +
                "}]";
    }
}

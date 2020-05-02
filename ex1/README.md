### Exercício 1

#### Instruçoes

Para testar o projeto basta fazer um checkout do repositóro, 

fazer um build do projeto, como utilizei maven, dentro da pasta do projeto ex1, utilizar o comando: mvn install

para executar os testes unitários separadamente, utilizar o comando: mvn test

#### Nota inicial

Como devemos manter o comportamento já existente do sistema, a primeira coisa que fiz foi criar testes unitários para garantir que ao final da minha alteração o comportamento seja o mesmo.
Identifiquei 3 funcionalidades iniciais:
* Ao receber uma lista vazia retornar uma string vazia
* Ao receber uma lista com apenas um numero retornar um texto pré definido no singular incluindo o número da nota fiscal 
* Ao receber uma lista com mais de um numero retornar um texto pré definido no plural incluindo os números das notas fiscais

Então implementei os respectivos testes unitários 

* GeradorObservacaoTest.mantemFuncionalidadeListaVaziaTest();
* GeradorObservacaoTest.mantemFuncionalidadeListaNaoVaziaSize1Test()
* GeradorObservacaoTest.mantemFuncionalidadeListaNaoVaziaTest()

criei os pacotes exception que contém uma Exception criada, services com subPacotes para cada serviço criado com suas interfaces e classes abstratas e valueObjects que contém um objeto 

#### Observaçoes

##### Problema

O primeiro problema encontrado foi que a classe não estava aberta a extensões, para adicionar uma nova funcionalidade eu teria que alterar o comportamento da classe e assim a cada nova funciolidadade eu teria uma nova modificação.

##### Solução

Criei uma classe abstrada chamada GenericGeradorObservacao, implementei lá os métodos universáis que seriam utilizados por qualquer classe responsável por esse tipo de serviço: geraObservacao() e retornaCodigos(), retirei a responsabilidade de encontrar o separador do método retornaCodigos() e criei o método abstrato montaSeparador(). Aqui encontrei outro problema, o método retornaCodigos() tinha mais de uma responsabilidade (encontrar o separador e retornar o código) e então eu teria dois motivos para alterar esse método, com essa separação eu deixei o método com apenas uma responsabilidade e deixei a funcionalidade montaSeparador() extensível, podendo ser implementada usando regras de negócio expecíficas.

Agora pude usar a classe GeradorObservacao como uma subClasse e lá implementei o método montaSeparador() com as regras de negócio originais.

Assim pude criar a classe GeradorObservacaoComValor como uma subClasse tambem e lá implementei o método montaSeparador() com as regras de negócio da nova funcionalidade. Para isso criei um objeto Remessa para ser o tipo da lista de entrada que possui um numero e valor como requisitado pela nova funcionalidade, esses atributos não se alteram então o objeto não tem o métodos set() e por isso chamei o pacote de valueObjects.  

Assim, crisei os testes unitários para a nova funcionalidade GeradorObservacaoComValorTest e garanti que os códigos já estavam coexistindo, porém, encontrei outros problemas

##### Problema
A classe GenericGeradorObservacao que originou-se da classe dada tinha mais de uma responsabilidade (verificar se a lista está vazia, definir o texto a ser retornado e encontrar os separadores), imagine por exemplo, que a regra sobre a lista vazia mude e agora eu tambem retorne uma string vazia quando a lista for muito grande, teria um motivo para alterar a classe, ou que o texto seria definido pela lingua do usuario e nao mais plural ou singular, outro motivo para alterar.

##### Solução
Criei outros dois serviços: DefinidorDeTexto e Validador que agora são responsáveis por definir o texto a ser retornado com base no tamanho da lista e verificar se a lista está vazia respectivamente. 

##### Problema

Apenas isso não bastaria pois a classe GenericGeradorObservacao estaria dependendo da implementação desses serviços
 
##### Solução

Criei uma interface para cada serviço e fiz a injeção de dependências na classe GenericGeradorObservacao, para isso usei o Spring.
 
Agora a classe está com apenas uma responsabilidade, esta aberta a extensão e depende de abstrações e não implementações.

Esse foi o único momento em que tive que alterar os testes iniciais pois como retirei responsabilidades extras da classe eu não teria que testa-las alí então fiz um Mock dos outros serviçoe e criei testes unitários para eles: DefinidorDeTextoTest e ValidadorTest

##### Problema
A classe não lidava com tipos de entrada invalido, como a entrada é um objeto do tipo List poderia ser uma list de qualquer tipo porém o método original lida com uma lista de inteiros e o novo métido lida com uma lista de Remessa 

##### Solução
Criei uma Exception e chamei de WrongDataTypeException, caso o método receba um tipo inválido em alguma posição da lista de entrada eu lanço essa exception, na classe GeradorObservacao a exception será lançada caso alguma posição contenha um tipo diferente de inteiro e em GeradorObservacaoComValor caso alguma posição contenha um tipo diferente de Remessa.

Criei testes unitários para validar esse comportamento nas duas classes: testaEntradaComTipoInvalidoTest1() e testaEntradaComTipoInvalidoTest2(). 
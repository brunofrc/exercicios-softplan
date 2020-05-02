### Exercício 2

#### Instruçoes

Para testar o projeto basta fazer um checkout do repositóro, fazer um build do projeto, como utilizei maven,  dentro da pasta do projeto ex2, utilizar o comando: mvn install.

Para executar os testes unitários separadamente, utilizar o comando: mvn test.

Para testar o exercício completo com a leitura do arquivo basta rodar o método main da classe Main.

#### Notas e observações

Utilizei o Spring mais uma vez para injeção de dependências e por isso, a classe Main implementa a interface CommandLineRunner, assim consigo usar o Bean da minha camada de aplicação em um método não estático, o método run().

Seguindo o modelo DDD dividi a implementação em camadas:

#### Camada de aplicação

Responsável por criar uma comunicação entre a interface do usuário, que no caso é a classe Main com o método main, com a camada de domínio. Criei a classe ApplicationController que implementa a interface IApplicationController.

#### Camada de domínio
Reponsável por implementar todas as regras de negócios, como no exercício temos apenas um contexto, nāo dividi essa camada por contextos.

Esse contexto foi dividido entre:

* entities: contendo os objetos Composiçao e ItemComposição;
* exception: contendo uma execeção que utilizei para retornar erro caso a entrada json não seja válida;
* services: aqui dividi em 3 serviços, o de composição responsável por retornar informações da composição, por exemplo o valor total; o de gerar preço detalhado, responsável por montar a resposta ao usuário contendo as informações detalhadas do preço das composições e o de precessar o json, responsável por receber um json e converter-lo em uma coleçao de objetos do tipo Composição com seus items do tipo ItemComposição.

Cada serviço possui apenas uma responsabilidade e depende apenas de abstrações de outros serviços, por isso fiz essa divisão.

#### Camada de infra
Responsável por manipular os registros do tipo Composição.

Nessa camada eu crirei a classe ComposicaoRepository contendo métodos para me auxiliar na manipulação dos registros encontrados de Composicao, para guardar e manipular os objetos eu criei uma coleção do tipo Set<Composicao> e não utilizei banco de dados nem arquivos pois não era necessario (caso algum dia seja, a alteração será feita aqui e não na camada de domínio)

#### Funcionamento
Na classe Main, recebemos o json localizado na pasta resources e enviamos para a ApplicationController com o objetivo de gerar o preço detalhado da obra, a ApplicationController envia o json para a camda de dominio através do servico GeraPrecoDetalhadoService, no método geraPrecoDetalhadoComposicao() esse servico invoca o serviço ProcessaJsonService, que ao receber um json, mapeia os items e utiliza os métodos do repositório para gerar uma lista de composicoes contendo seus items e retorna para o serviço GeraPrecoDetalhadoService. Com a lista de composiçoes pronta só falta calcular o valor total de cada uma e gerar o relatório, para isso para cada composicao é invocado o serviço ComposicaoService que calcula o preco total e retorna para GeraPrecoDetalhadoService que devolve para o usuário uma string com todos os detalhes.  
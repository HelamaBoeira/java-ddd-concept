
# Aplicação conceito cadastro de Pessoas

Um projeto conceito implementado em java que realiza o cadastro de pessoas através de uma API ou uma interface frontend JSF/primefaces




## Dependências

* Java 17
* Mavem 3.8.1
* Docker


## Desenvolvimento

A aplicação usa spring boot então pode ser executada a partir da classe main **CadastroApplication** o banco de dados é provisionado no docker a partir do arquivo docker-compose.yml dentro da pasta *run-environment* encontrada na raiz do projeto
## Arquitetura
A aplicação foi desenvolvida a partir do conceito de DDD e da suas camadas

**Domain**: é a camada onde se encontra as entidades de negócio e os contratos para suas manipulações, a camada de domínio não referencia nenhuma das outras, porem pode ser referencia por elas(exceto a camada de apresentação)

**Infra**: é a camada responsável pela comunicação externa da aplicação seja com banco de dados, mensagens, arquivos e afins.

**Application**: é a camada que representa o fluxo de execuções da aplicação e também é responsável por fornecer contratos e acesso a ao domínio pela camada de apresentação.
A camada é divida em 2 subcamadas
* Apps
* Services

Services são responsáveis pela comunicação com o domínio e onde as regras de negócio podem ser implementadas

Apps são responsáveis por fornecer interfaces para que as camadas de apresentação
possam solicitar operações ao domínio, ela também é responsável por converter os contratos DTO para entidades de domínio.

**Presentation**: é a camada responsável pelas interações com os usuários seja através de uma API ou do JSF, essa camada se comunica apenas com a aplicação recebendo e enviando contratos DTOs


**API**: responsável pelo CRUD de usuário seguindo o conceito REST-Full

**JSF**: responsável pelo CRUD de usuários implementado formulários xhtml com a biblioteca primefaces, os arquivos de front podem ser encontrados dentro de src/main/webapp

## Deploy

É possivel fazer o deploy da aplicação através de containers docker com o arquivo docker-compose

Para isso é necessário primeiro executar na raiz do projeto o comando

```bash
  mvn clean install
```

Em seguinda entrando na pasta run-environment/app você pode executar o comando
```bash
  docker-compose up
```

A aplicação ficará disponível na porta 9090, a API pode ser acessada pelo **/api/pessoas** e o frontend pode ser acessado pelo **/index.xhmtl**
## Documentação da API

#### Retorna todos os itens

```http
  GET /api/pessoas
```

#### Retorna todas as pessoas cadastradas

```http
  GET /api/pessoas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID da pessoa |

#### Retorna uma pessoa cadastrada

```http
  DELETE /api/pessoas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID da pessoa |

#### Exclui uma pessoa cadastrada, se a pessoa não existir não faz nada


```http
  POST /api/pessoas
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `RequestBody`      | `PessoaDTO` | **Obrigatório**. entidade pessoa |

#### Cria uma pessoa


```http
  PUT /api/pessoas
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `RequestBody`      | `PessoaDTO` | **Obrigatório**. entidade pessoa |

#### Atualiza uma pessoa já cadastrada, caso a pessoa não exista IlegalArgumentException


## Front End

Para acessar o front da aplicação basta entrar na URL **localhost:port/index.xhtml**
isso irá abrir uma tela com a listagem de pessoas e através dela você consegue executar as demais operações.

* Criar: para criar uma pessoa clique no botão Novo do lado superior esquerdo da tela, voce será redirecionado para outro arquivo **/cadastro.xhtml** com o cadastro realizado será redirecionado para a tela da listagem novamente.

* Editar: para editar basta clicar no icone do lapiz que se encontra em cada registro exibido na tabela, você será redirecionado para  **/cadastro.xhtml**  e após a conclusão retorná a pagina anterior

* Visualizar: para visualizar todas as informações de uma pessoa cadastrada basta clicar no ícone do olho encontrado em cada registro da tabela que será aberta uma modal exibindo todas as informações.

* Excluir: para excluir clique no icone da lixeira encontrado no mesmo lugar das ações acima, será aberta uma janela de confirmação, clicando em **Sim** o registro será excluído, clicando em **Não** a operação não é realizada
## Testes Automatizados
Para executar os testes automatizados unitários e de integração basta apenas rodar o comando

```bash
  mvn clean install
```

Um banco de dados H2 será disponibilizado para a execução dos testes não sendo necessário subir nenhuma aplicação de apoio
## Comentários do Autor

Optei por desenvolver usando spring boot por ser uma framework que tenho domínio como também fornece muitos recursos e integrações, particularmente nunca tinha integrado com JSF/primefaces, mas acabou dando certo sem maiores complicações.

Já fazia um bom tempo que não implementava frontend e primefaces eu não tinha trabalhado antes então foi uma experiência nova para mim, reconheço que o código front precisa evoluir, mas esta funcional e executando todas as operações propostas.

A ideia de implementar para além do JSF uma api foi para mostrar o conceito da camada de apresentação abstraída do resto e ambos os conceitos utilizando a camada de aplicação sem duplicação de código e garantindo integridade.

A implementação do docker fornece um ambiente limpo e simulado de um deploy sendo possivel excutar testes manuais tranquilamente.
## Melhorias
Algumas questões não implementadas devido ao tempo.

No geral faltaram validações na API no front e também na camada de aplicação para garantir a integridade das entidades de negócio.

Tive problemas com os conversores personalizados para campos no front em especial do LocalDate, o spring não carregava as classes facesconverter acabei.

A tabela do front pode ter mais filtros.

O Docker para além de subir a aplicação poderia buildar ela com o mavem tambem.

Os testes das classes bean, acabei criando apenas unitários de forma conceitual.

As camadas poderiam ser distribuídas em projetos para assim manter vários arquivos pom.xml menores e melhorando a visibilidades das dependências usadas

Faltaram algumas querys personalizadas no JPA, como busca por cidade, sexo e estado. 





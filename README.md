# Comparacao de Itens

API backend simplificada para retornar detalhes de produtos e apoiar recursos de comparacao entre itens.

## Objetivo

Disponibilizar um ponto de extremidade REST que permita consultar, em uma unica requisicao, informacoes de varios produtos, retornando dados suficientes para apresentacoes de comparacao.

## Tecnologias
- Java 21
- Spring Boot 3
- Maven
- H2 Database (memoria)
- Docker
- Clean Architecture e DDD (camadas de dominio, aplicacao e infraestrutura)
- Testes unitarios e de integracao

## Estrategia e arquitetura
- **Dominio**: modelos `ProdutoDomain` e `EspecificacaoDomain` representam as entidades de negocio.
- **Aplicacao**: caso de uso `ConsultarProdutosUseCase` orquestrado pelo `ConsultarProdutosService`.
- **Infraestrutura**: persistencia com Spring Data JPA (`ProdutoJpaRepository`) e adaptador `ProdutoRepositoryImpl`; controlador REST `ProdutoController` exposto em `/api/produtos/comparacao` e `/api/produtos/filtro`.
- **Dados**: base H2 em memoria com carga inicial em `data.sql`.

## Ponto de extremidade da API
`GET /api/produtos/comparacao?ids={id1}&ids={id2}`

- Parametro `ids` opcional e repetivel. Quando omitido, todos os produtos disponiveis sao retornados.
- Resposta: lista JSON contendo `id`, `nome`, `urlImagem`, `descricao`, `preco`, `classificacao` e `especificacoes` (nome/valor).

`GET /api/produtos/filtro?nome={texto}&descricao={texto}&precoMaximo={valor}&classificacaoMinima={valor}`

- Todos os parametros sao opcionais e podem ser combinados para filtrar por nome, descricao, preco maximo e classificacao minima.
- Resposta: lista JSON com os mesmos campos do endpoint de comparacao.

### Exemplo de resposta
```json
[
  {
    "id": 1,
    "nome": "Smartphone X",
    "urlImagem": "https://example.com/imagens/smartphone-x.png",
    "descricao": "Smartphone com tela OLED e camera dupla.",
    "preco": 3599.9,
    "classificacao": 4.5,
    "especificacoes": [
      { "nome": "Tela", "valor": "6.1 polegadas OLED" }
    ]
  }
]
```

## Como executar
### Via Maven
```bash
mvn spring-boot:run
```

### Via Docker
```bash
docker build -t comparador-produtos .
docker run -p 8080:8080 comparador-produtos
```

## Testes
- Testes unitarios cobrem a camada de aplicacao (`ConsultarProdutosServiceTest`).
- Teste de integracao valida o endpoint REST com base H2 real (`ProdutoControllerIntegrationTest`).

Para executar:
```bash
mvn test
```

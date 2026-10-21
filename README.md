# Sistema de Controle de Projetos (AC2)

Backend em Spring Boot 3 (Web, Data JPA, H2, Validation, Lombok) e protótipo de tela em `src/main/resources/static/index.html`.

## Como executar
Java 17 e Maven:

```
mvn spring-boot:run
```

Acesse http://localhost:8080 (tela de projetos). O banco H2 é em memória; console em `/h2-console` com a URL `jdbc:h2:mem:projetos`, usuário `sa`.

## Endpoints
| Método | Rota | Descrição |
|---|---|---|
| POST | `/setores` | adicionar setor |
| GET | `/setores/{id}` | setor com seus funcionários |
| GET | `/setores` | lista setores com funcionários |
| POST | `/funcionarios` | adicionar funcionário (`nome`, `idSetor`) |
| GET | `/funcionarios/{id}/projetos` | projetos do funcionário |
| POST | `/projetos` | adicionar projeto (`descricao`, `dataInicio`, `dataFim`) |
| GET | `/projetos` | listar projetos |
| GET | `/projetos/{id}` | projeto com funcionários vinculados |
| GET | `/projetos/periodo?inicio=&fim=` | projetos com início no intervalo |
| POST | `/projetos/{idProjeto}/funcionarios/{idFuncionario}` | vincular funcionário |

Erros retornam JSON: `{timestamp, status, mensagem, erros[]}`.

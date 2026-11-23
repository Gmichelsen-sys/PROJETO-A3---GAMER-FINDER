# Game Finder — Projeto A3

O **Game Finder** é um projeto acadêmico desenvolvido em Java com o objetivo de ajudar jogadores a encontrar jogos que combinem com suas preferências. O usuário responde a um questionário e o sistema utiliza essas respostas para buscar e analisar títulos, apresentando uma lista de recomendações.

## Objetivo

Reduzir a indecisão na escolha de jogos por meio de um questionário de preferências e um algoritmo de compatibilidade desenvolvido em Java.

## Como funciona

1. O programa apresenta perguntas sobre preferências e estilo de jogo.
2. As respostas são reunidas pelo sistema.
3. O Game Finder consulta a **RAWG API**, que funciona como seu catálogo externo de jogos.
4. As informações recebidas são interpretadas e organizadas em objetos Java.
5. O algoritmo próprio compara os jogos com as preferências informadas.
6. Os resultados são pontuados, ordenados e apresentados ao usuário, destacando os mais compatíveis.

> **Importante:** a RAWG fornece os dados dos jogos. A lógica de compatibilidade e a decisão de recomendação são realizadas pelo Game Finder, por meio do algoritmo Java. A API não é o algoritmo de recomendação nem um banco de dados local do projeto.

## Tecnologias e conceitos

- Java
- RAWG API (consulta externa de catálogo de jogos)
- HTTP para comunicação com a API
- JSON para formato dos dados recebidos
- Gson para interpretação dos dados JSON (quando utilizado na versão atual)
- Arrays e ArrayList
- Classes e objetos
- Laços de repetição (`for`)
- Condicionais (`if`)
- Métodos
- Comparação, pontuação e ordenação

## Estrutura das classes

| Classe | Responsabilidade |
|---|---|
| `Main.java` | Ponto de entrada e coordenação do fluxo do programa |
| `Questionario.java` | Define as perguntas e opções apresentadas ao usuário |
| `ApiRawg.java` | Faz a consulta à RAWG API e disponibiliza os jogos recebidos |
| `Jogo.java` | Representa os dados de cada jogo em objetos Java |
| `Recomendador.java` | Compara jogos com as preferências, calcula compatibilidade e ordena os resultados |

## Fluxo do sistema

```text
QUESTIONÁRIO
    ↓
RESPOSTAS DO USUÁRIO
    ↓
CONSULTA À RAWG API
    ↓
DADOS JSON → OBJETOS JOGO
    ↓
ALGORITMO DE COMPATIBILIDADE
    ↓
PONTUAÇÃO E ORDENAÇÃO
    ↓
JOGOS RECOMENDADOS
```

## Executar o projeto

### Requisitos

- JDK instalado (compatível com o código do projeto)
- Visual Studio Code
- Extensão **Extension Pack for Java**
- Dependências Java utilizadas pela versão atual, incluindo Gson quando necessário
- Chave de acesso da RAWG API, caso a versão executada solicite autenticação

### No VS Code

1. Clone ou baixe este repositório.
2. Abra a pasta do projeto no VS Code.
3. Configure as dependências da versão atual.
4. Configure sua chave da RAWG API sem publicá-la no código ou no repositório.
5. Execute a classe `Main.java`.

> As instruções exatas de compilação podem variar conforme a configuração de dependências adotada no projeto.

## Sobre o algoritmo

O percentual apresentado pelo Game Finder representa uma **pontuação heurística de compatibilidade**, calculada a partir dos critérios programados. Não é uma probabilidade estatística nem uma garantia de que o usuário gostará do jogo.

## Escopo do MVP

- Execução no terminal
- Questionário de preferências
- Consulta a catálogo externo por API
- Organização dos dados em objetos Java
- Cálculo de compatibilidade e ordenação de resultados

O MVP não possui, nesta etapa, login, matchmaking ou banco de dados local próprio.

## Projeto acadêmico

Projeto A3 — Game Finder / Gamer Finder  
Desenvolvido como exercício de aplicação dos conceitos de programação Java, organização em classes, estruturas de dados, integração externa e lógica algorítmica.

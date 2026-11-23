# Game Finder — Projeto A3

O Game Finder é um projeto acadêmico Java para recomendar jogos com base nas respostas de um questionário. O catálogo é consultado pela API RAWG; o cálculo de compatibilidade é feito pelo algoritmo Java do projeto.

## Fluxo
1. Usuário responde ao questionário.
2. O programa seleciona o gênero para consulta.
3. A classe ApiRawg consulta a RAWG e interpreta o JSON usando Gson.
4. Os jogos são convertidos em objetos Jogo.
5. Recomendador calcula compatibilidade por gênero, plataforma e nota.
6. Os resultados são ordenados e até cinco recomendações são exibidas.

## Estrutura
- `src/Main.java`: fluxo principal e interação no terminal.
- `src/Questionario.java`: perguntas e opções.
- `src/ApiRawg.java`: consulta HTTP à API RAWG e parsing JSON.
- `src/Jogo.java`: classe-modelo de jogo.
- `src/Recomendador.java`: pontuação e ordenação de compatibilidade.
- `lib/`: coloque a biblioteca Gson aqui.

## Requisitos
- JDK 17 ou superior.
- VS Code com Extension Pack for Java.
- Chave da API RAWG.
- Gson 2.x (arquivo JAR; não incluído nesta versão do repositório).

## Configuração da RAWG
Defina sua chave de API na variável de ambiente `RAWG_API_KEY`. Não insira a chave diretamente no código e não a publique no repositório.

### Windows PowerShell
```powershell
$env:RAWG_API_KEY = "SUA_CHAVE_RAWG"
cd src
javac -cp "..\lib\gson-2.13.2.jar" *.java
java -cp ".;..\lib\gson-2.13.2.jar" Main
```

### Linux/macOS
```bash
export RAWG_API_KEY="SUA_CHAVE_RAWG"
cd src
javac -cp ../lib/gson-2.13.2.jar *.java
java -cp .:../lib/gson-2.13.2.jar Main
```

## Pontuação
O percentual é uma pontuação heurística de compatibilidade (gênero: 40 pontos; plataforma: 30; avaliação RAWG: até 30), não uma probabilidade estatística de satisfação.

## Escopo MVP
Terminal, questionário, consulta de catálogo externo e recomendações. Não inclui login, matchmaking ou banco de dados local.

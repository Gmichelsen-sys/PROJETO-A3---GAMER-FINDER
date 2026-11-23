import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("       GAME FINDER - JAVA");
        System.out.println("====================================");

        String[] respostas = new String[Questionario.PERGUNTAS.length];

        // Questionario
        for (int i = 0; i < respostas.length; i++) {

            System.out.println("\n" + (i + 1) + ". " + Questionario.PERGUNTAS[i]);

            for (int k = 0; k < Questionario.OPCOES[i].length; k++) {
                System.out.println("  " + (k + 1) + ") " + Questionario.OPCOES[i][k]);
            }

            int escolha = 0;

            while (escolha < 1 || escolha > Questionario.OPCOES[i].length) {
                System.out.print("Escolha o numero da opcao: ");
                String entrada = scanner.nextLine();

                try {
                    escolha = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    escolha = 0;
                }

                if (escolha < 1 || escolha > Questionario.OPCOES[i].length) {
                    System.out.println("Opcao invalida. Tente novamente.");
                }
            }

            respostas[i] = Questionario.OPCOES[i][escolha - 1];
        }

        // Identificar genero escolhido
        String genero = "";

        switch (respostas[3]) {
            case "FPS / Acao":
                genero = "action";
                break;
            case "RPG / Aventura":
                genero = "role-playing-games-rpg";
                break;
            case "Corrida / Esportes":
                genero = "racing";
                break;
            case "Simulacao / Estrategia":
                genero = "simulation";
                break;
        }

        System.out.println("\n====================================");
        System.out.println("Relaxe, estamos procurando jogos para voce...");
        System.out.println("====================================");

        ArrayList<Jogo> jogos = ApiRawg.buscarJogos(genero);

        ArrayList<Recomendador.Resultado> recomendacoes =
                Recomendador.recomendar(jogos, respostas[3], respostas[2]);

        System.out.println("\n=== SEUS 5 JOGOS RECOMENDADOS ===");

        if (recomendacoes.isEmpty()) {
            System.out.println("Nenhum jogo encontrado para seu perfil.");
        } else {
            int posicao = 1;
            for (Recomendador.Resultado item : recomendacoes) {
                Jogo jogo = item.jogo;
                System.out.println("\n---------------------------");
                System.out.println(posicao + ". " + jogo.nome);
                System.out.println("Compatibilidade: " + item.percentual + "%");
                System.out.println("Nota RAWG: " + jogo.nota);
                System.out.println("Lancamento: " + jogo.lancamento);
                System.out.println("Genero: " + jogo.generos);
                System.out.println("Plataformas: " + jogo.plataformas);
                posicao++;
            }
        }

        System.out.println("\nTotal de jogos consultados: " + jogos.size());
        System.out.println("Obrigado por usar o Game Finder!");
        scanner.close();
    }
}
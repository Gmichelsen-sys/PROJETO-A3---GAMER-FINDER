import java.util.ArrayList;

public class Recomendador {

    public static ArrayList<Resultado> recomendar(
            ArrayList<Jogo> jogos,
            String generoEscolhido,
            String plataformaEscolhida) {

        ArrayList<Resultado> resultados = new ArrayList<>();

        for (Jogo jogo : jogos) {
            int pontos = 0;

            // Compatibilidade de genero - 40 pontos
            if (combinaGenero(jogo.generos, generoEscolhido)) {
                pontos += 40;
            }

            // Compatibilidade de plataforma - 30 pontos
            if (combinaPlataforma(jogo.plataformas, plataformaEscolhida)) {
                pontos += 30;
            }

            // Nota RAWG - ate 30 pontos
            int pontosNota = (int) Math.round((jogo.nota / 5.0) * 30);
            pontos += pontosNota;

            if (pontos > 100) {
                pontos = 100;
            }

            resultados.add(new Resultado(jogo, pontos));
        }

        // Ordenar do maior percentual para o menor
        resultados.sort((a, b) -> Integer.compare(b.percentual, a.percentual));

        // Retornar ate 5 jogos
        return new ArrayList<>(resultados.subList(0, Math.min(5, resultados.size())));
    }

    private static boolean combinaGenero(String generos, String escolhido) {
        generos = generos.toLowerCase();

        switch (escolhido) {
            case "FPS / Acao":
                return generos.contains("action") || generos.contains("shooter");
            case "RPG / Aventura":
                return generos.contains("rpg") || generos.contains("adventure");
            case "Corrida / Esportes":
                return generos.contains("racing") || generos.contains("sports");
            case "Simulacao / Estrategia":
                return generos.contains("simulation") || generos.contains("strategy");
            default:
                return false;
        }
    }

    private static boolean combinaPlataforma(String plataformas, String escolhida) {
        plataformas = plataformas.toLowerCase();
        escolhida = escolhida.toLowerCase();

        switch (escolhida) {
            case "pc":
                return plataformas.contains("pc");
            case "ps5":
                return plataformas.contains("playstation 5") || plataformas.contains("ps5");
            case "xbox":
                return plataformas.contains("xbox");
            default:
                return false;
        }
    }

    public static class Resultado {
        public final Jogo jogo;
        public final int percentual;

        public Resultado(Jogo jogo, int percentual) {
            this.jogo = jogo;
            this.percentual = percentual;
        }
    }
}
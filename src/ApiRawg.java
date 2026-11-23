import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiRawg {

    public static ArrayList<Jogo> buscarJogos(String genero) {

        ArrayList<Jogo> jogos = new ArrayList<>();
        String chaveApi = System.getenv("RAWG_API_KEY");

        if (chaveApi == null || chaveApi.isBlank()) {
            System.out.println("Configure a variavel de ambiente RAWG_API_KEY antes de executar.");
            return jogos;
        }

        String endereco = "https://api.rawg.io/api/games"
                + "?key=" + chaveApi
                + "&page_size=5"
                + "&genres=" + genero;

        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .GET()
                    .build();

            HttpResponse<String> resposta = cliente.send(
                    requisicao,
                    HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("Status HTTP: " + resposta.statusCode());

            if (resposta.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(resposta.body()).getAsJsonObject();
                JsonArray resultados = json.getAsJsonArray("results");
                System.out.println("Jogos encontrados: " + resultados.size());

                for (int i = 0; i < resultados.size(); i++) {
                    JsonObject jogoJson = resultados.get(i).getAsJsonObject();
                    String nome = jogoJson.get("name").getAsString();
                    double nota = jogoJson.get("rating").isJsonNull()
                            ? 0 : jogoJson.get("rating").getAsDouble();
                    String lancamento = jogoJson.get("released").isJsonNull()
                            ? "Sem data" : jogoJson.get("released").getAsString();
                    String imagem = jogoJson.get("background_image").isJsonNull()
                            ? "" : jogoJson.get("background_image").getAsString();

                    String generos = "";
                    JsonArray listaGeneros = jogoJson.getAsJsonArray("genres");
                    for (int g = 0; g < listaGeneros.size(); g++) {
                        generos += listaGeneros.get(g).getAsJsonObject()
                                .get("name").getAsString();
                        if (g < listaGeneros.size() - 1) generos += ", ";
                    }

                    String plataformas = "";
                    JsonArray listaPlataformas = jogoJson.getAsJsonArray("platforms");
                    if (listaPlataformas != null) {
                        for (int p = 0; p < listaPlataformas.size(); p++) {
                            JsonObject plataformaJson = listaPlataformas.get(p).getAsJsonObject();
                            plataformas += plataformaJson.getAsJsonObject("platform")
                                    .get("name").getAsString();
                            if (p < listaPlataformas.size() - 1) plataformas += ", ";
                        }
                    }

                    Jogo jogo = new Jogo(nome, nota, lancamento, generos, plataformas, imagem);
                    jogos.add(jogo);
                }
            } else {
                System.out.println("Erro na API: " + resposta.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar com a RAWG.");
            e.printStackTrace();
        }
        return jogos;
    }

    public static void main(String[] args) {
        ArrayList<Jogo> jogos = buscarJogos("role-playing-games-rpg");
        for (Jogo jogo : jogos) {
            System.out.println("---------------------------");
            System.out.println("Nome: " + jogo.nome);
            System.out.println("Nota: " + jogo.nota);
            System.out.println("Lancamento: " + jogo.lancamento);
            System.out.println("Generos: " + jogo.generos);
            System.out.println("Plataformas: " + jogo.plataformas);
            System.out.println("Imagem: " + jogo.imagem);
        }
    }
}
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    private static Map<String, String> map;

    public static void main(String[] args) throws Exception {
        
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        // Top250
        //String url = "https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json";
        // Most Populars
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Imprimir o JSON recuperado.
        // System.out.println(body);

        // Extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(body);

        // System.out.println(listaDeFilmes.size());
        // System.out.println(listaDeFilmes.get(0));

        // Exibir e manipular os dados.
        var geradora = new GeradoradeFigurinhas();

        //for (Map<String,String> filme : listaDeFilmes) {
        for(int i =0; i< 3; i++){
            Map<String,String> filme = listaDeConteudos.get(i);
            String urlImagem = filme.get("image")
             .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String titulo = filme.get("title");
            
            String nomeArquivo = titulo + ".png";
           
            
            try{
                InputStream inputStream = new URL(urlImagem).openStream();
                System.out.println("Gerando imagem - [" + titulo + "]");
                geradora.cria(inputStream, nomeArquivo);
             }catch(java.io.FileNotFoundException err){
                  System.out.println("Imagem não encontrada ou link inválido");
             }

            System.out.println(titulo);
            System.out.println();
      
        }
        //}
    }
}
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileInputStream;
import java.io.InputStream;
//import java.net.URL;
import java.awt.Color;
//import java.io.IOException;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoradeFigurinhas {
    //private BufferedImage bufferedImage;

    /**
     * @throws Exception
     */
    void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        //leitura da Imagem
        //BufferedImage imagemOriginal =   ImageIO.read(new File("entrada/filme.jpg"));
        //InputStream inputStream=new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream=  new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal =   ImageIO.read(inputStream);

        // cria nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura,novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        
        // escrever uma frase nova imagem
        graphics.drawString("Topzera", 0, novaAltura -100);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem,"png", new File(nomeArquivo));
    

    }
    

}

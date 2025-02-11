package meujogo.modelo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosao {
    private int x, y;
    private Image[] frames;
    private int frameAtual = 0;
    private int duracaoFrame = 5;  // Tempo entre cada frame
    private int contador = 0;
    private boolean ativa = true;

    public Explosao(int x, int y, Image spritesheet, int linhas, int colunas) {
        this.x = x;
        this.y = y;
        this.frames = cortarFrames(spritesheet, linhas, colunas);
    }

    // Método que corta a spritesheet corretamente
    private Image[] cortarFrames(Image spritesheet, int linhas, int colunas) {
        int larguraFrame = spritesheet.getWidth(null) / colunas;
        int alturaFrame = spritesheet.getHeight(null) / linhas;
        Image[] frames = new Image[linhas * colunas];

        BufferedImage sheet = new BufferedImage(spritesheet.getWidth(null), spritesheet.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = sheet.getGraphics();
        g.drawImage(spritesheet, 0, 0, null);
        g.dispose();

        int index = 0;
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                frames[index++] = sheet.getSubimage(coluna * larguraFrame, linha * alturaFrame, larguraFrame, alturaFrame);
            }
        }

        return frames;
    }

    public void atualizar() {
        if (ativa) {
            contador++;
            if (contador >= duracaoFrame) {
                contador = 0;
                frameAtual++;
                if (frameAtual >= frames.length) {
                    ativa = false; // Para a animação quando chega ao fim
                }
            }
        }
    }

    public boolean terminou() {
        return !ativa;
    }

    public void desenhar(Graphics g) {
        if (ativa) {
            g.drawImage(frames[frameAtual], x, y, null);
        }
    }
}

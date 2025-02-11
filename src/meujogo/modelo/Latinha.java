package meujogo.modelo;

import javax.swing.*;

public class Latinha extends Lixo {

    public Latinha(int x, int y) {
        super(x, y);
        carregarImagem();
    }

    @Override
    public void carregarImagem() {
        ImageIcon referencia = new ImageIcon("res\\modelos\\Latinha.png");
        imagem = referencia.getImage();
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
    }
}
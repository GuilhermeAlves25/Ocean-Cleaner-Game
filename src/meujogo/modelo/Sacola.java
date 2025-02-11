package meujogo.modelo;

import javax.swing.*;

public class Sacola extends Lixo {

    public Sacola(int x, int y) {
        super(x, y);
        carregarImagem();
    }

    @Override
    public void carregarImagem() {
        ImageIcon referencia = new ImageIcon("res\\modelos\\Sacola.png");
        imagem = referencia.getImage();
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
    }
}
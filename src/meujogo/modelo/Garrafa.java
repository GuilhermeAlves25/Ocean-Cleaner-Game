package meujogo.modelo;

import javax.swing.*;

public class Garrafa extends Lixo {

    public Garrafa(int x, int y) {
        super(x, y);
        carregarImagem();
    }

    @Override
    public void carregarImagem() {
        ImageIcon referencia = new ImageIcon("res\\modelos\\Garrafa.png");
        imagem = referencia.getImage();
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
    }
}
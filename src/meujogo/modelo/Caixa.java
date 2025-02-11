package meujogo.modelo;

import javax.swing.*;

public class Caixa extends Lixo {

    public Caixa(int x, int y) {
        super(x, y);
        carregarImagem();
    }

    @Override
    public void carregarImagem() {
        ImageIcon referencia = new ImageIcon("res\\modelos\\Caixa.png");
        imagem = referencia.getImage();
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
    }
}
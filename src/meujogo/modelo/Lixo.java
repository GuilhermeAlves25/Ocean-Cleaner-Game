package meujogo.modelo;

import java.awt.*;

public abstract class Lixo {

    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean visivel;

    public Lixo(int x, int y) {
        this.x = x;
        this.y = y;
        visivel = true;
    }

    public abstract void carregarImagem();

    public void atualizar() {
        x -= 4; // Velocidade padrão de movimento
        if (x < -largura) {
            visivel = false; // Saiu da tela
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
package meujogo.modelo;

import javax.swing.*;
import java.awt.*;

public class Tiro {

    private Image imagen;
    private int x,y;
    private int largura,altura;
    private boolean isVisible;

    private static final int Largura = 1000;
    private static int velocidade = 15;


    public  Tiro(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }
    public void Load(){
        ImageIcon referencia = new ImageIcon("res\\modelos\\Tiro (2).png");
        imagen = referencia.getImage();

        this.largura = imagen.getWidth(null);
        this.altura = imagen.getHeight(null);

    }
    public void Update(){
        this.x += velocidade;
        if(this.x > Largura){
            isVisible = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public static int getVelocidade() {
        return velocidade;
    }

    public static void setVelocidade(int velocidade) {
        Tiro.velocidade = velocidade;
    }

    public Image getImagen() {
        return imagen;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }
}

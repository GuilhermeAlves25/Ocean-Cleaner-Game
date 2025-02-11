package meujogo.modelo;

import meujogo.controle.Som;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;



public class Player extends JPanel {

    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura,largura;
    private List <Tiro> tiros;
    private boolean isVisible;
    private int vida;
    private Som somtiro;

    public Player(Som somtiro) {
        this.x = 50;
        this.y = 50;
        this.altura = 100;
        this.largura = 100;
        tiros = new ArrayList<Tiro>();
        isVisible = true;
        this.vida = 5;
        this.somtiro = somtiro;
    }


    public void load(){
        ImageIcon referencia = new ImageIcon("res\\modelos\\SUB.png");
        imagem = referencia.getImage();

    }

    public void update() {
        x += dx;
        y += dy;

        // Define os limites da tela
        int larguraTela = 900; // Exemplo de largura da tela
        int alturaTela = 728; // Exemplo de altura da tela

        // Impede que o player saia pelos lados
        if (x < 0) {
            x = 0;
        }
        if (x + largura > larguraTela) {
            x = larguraTela - largura;
        }

        // Impede que o player saia pelas bordas superior e inferior
        if (y < 0) {
            y = 0;
        }
        if (y + altura > alturaTela) {
            y = alturaTela - altura;
        }
    }

    public void Tirosimples() {
        Tiro tiroExemplo = new Tiro(0, 0);
        tiroExemplo.Load(); // Carrega a imagem para obter a altura corretamente

        int tiroX = x + largura; // Ajusta o X para sair do centro do player
        int tiroY = y + (altura / 2) - (tiroExemplo.getAltura() / 2); // Centraliza verticalmente o tiro

        tiros.add(new Tiro(tiroX, tiroY)); // Adiciona o tiro ajustado na lista
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keypressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP){
            dy = -5;
        }
        if(codigo == KeyEvent.VK_DOWN){
            dy = 5;
        }
        if(codigo == KeyEvent.VK_LEFT){
            dx = -5;
        }
        if(codigo == KeyEvent.VK_RIGHT){
            dx = 5;
        }
    }
    public void keyrelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_A){
            somtiro.play();
            Tirosimples();
        }

        if(codigo == KeyEvent.VK_UP){
            dy = 0;
        }
        if(codigo == KeyEvent.VK_DOWN){
            dy = 0;
        }
        if(codigo == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if(codigo == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Image getImagem() {
        return imagem;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}

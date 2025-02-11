package meujogo.principal;

import meujogo.visao.TelaInicio;

import javax.swing.*;

public class Conteiner extends JFrame {

    public Conteiner() {
        setTitle("Meu jogo");
        setSize(1024, 728);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);

        // Adiciona a tela de início
        add(new TelaInicio(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        new Conteiner();  // Cria uma instância da janela
    }


}




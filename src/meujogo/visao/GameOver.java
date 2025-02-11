package meujogo.visao;

import meujogo.modelo.Fase;
import meujogo.principal.Conteiner;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    private JButton botaoVoltar;
    private JFrame frame;
    private Fase faseAtual; // Armazena a referência da fase
    private int Tipo;
    public GameOver(JFrame frame, Fase fase,int Tipo) {
        this.frame = frame;
        this.faseAtual = fase;
        this.Tipo =  Tipo;

        setLayout(new BorderLayout());

        if(Tipo == 1){
            ImageIcon morte = new ImageIcon("res/telas/morte.png");
            JLabel labelImagem = new JLabel(morte);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            add(labelImagem, BorderLayout.CENTER);
        }else if(Tipo == 2){
            ImageIcon imagemFracasso = new ImageIcon("res/telas/Fracasso.png");

            JLabel labelImagem = new JLabel(imagemFracasso);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            add(labelImagem, BorderLayout.CENTER);
        }else if(Tipo == 3){
            ImageIcon venceu = new ImageIcon("res/telas/Ganhou.png");

            JLabel labelImagem = new JLabel(venceu);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            add(labelImagem, BorderLayout.CENTER);
        }





        JPanel painelBotoes = new JPanel();
        botaoVoltar = new JButton("Reiniciar Jogo");
        botaoVoltar.addActionListener(e -> reiniciarJogo());

        painelBotoes.add(botaoVoltar);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void reiniciarJogo() {
        if (faseAtual != null) {
            faseAtual.finalizarJogo();  // Chama o método para limpar recursos
        }

        frame.dispose(); // Fecha a janela do jogo atual
        new Conteiner(); // Abre uma nova instância do jogo
    }
}
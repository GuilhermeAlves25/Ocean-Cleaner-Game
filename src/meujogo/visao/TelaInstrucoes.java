package meujogo.visao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInstrucoes extends JPanel {

    private JButton botaoVoltar;
    private JFrame frame;
    private JLabel labelImagem;

    public TelaInstrucoes(JFrame frame) {
        this.frame = frame;

        // Painel em camadas para sobrepor o botão
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1024, 728));

        // Carrega a imagem inicial das instruções
        ImageIcon imagemInstrucoes = new ImageIcon("res\\telas\\Instrucoes.png");
        labelImagem = new JLabel(imagemInstrucoes);
        labelImagem.setBounds(0, 0, 1024, 728);

        // Botão Voltar
        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(450, 620, 120, 30);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaInicio();
            }
        });



        JButton controles = new JButton();
        controles.setOpaque(false);//
        controles.setContentAreaFilled(false);//
        controles.setBorderPainted(false);
        controles.setBounds(30, 30, 150, 50);

        controles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trocarImagem("res\\telas\\Instrucoes.png");
            }
        });

        // Botão invisível sobre o botão "REGRAS"
        JButton botaoRegras = new JButton();
        botaoRegras.setOpaque(false);
        botaoRegras.setContentAreaFilled(false);
        botaoRegras.setBorderPainted(false);
        botaoRegras.setBounds(850, 640, 150, 50);

        botaoRegras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trocarImagem("res\\telas\\Regras.png");
            }
        });

        // Adiciona componentes ao painel em camadas
        layeredPane.add(labelImagem, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(botaoVoltar, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(botaoRegras, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(controles, JLayeredPane.PALETTE_LAYER);

        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
    }

    public void voltarParaInicio() {
        frame.getContentPane().removeAll();
        frame.add(new TelaInicio(frame));
        frame.revalidate();
        frame.repaint();
    }

    // Troca a imagem no painel
    public void trocarImagem(String caminhoNovaImagem) {
        labelImagem.setIcon(new ImageIcon(caminhoNovaImagem));
    }
}

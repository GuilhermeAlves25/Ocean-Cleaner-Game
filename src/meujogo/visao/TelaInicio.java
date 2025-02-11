package meujogo.visao;

import meujogo.modelo.Fase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicio extends JPanel {

    private JButton botaoJogar;
    private JButton botaoInstrucoes;
    private JFrame frame;

    public TelaInicio(JFrame frame) {
        this.frame = frame;

        // Define layout manual para posicionamento com imagem de fundo
        setLayout(new BorderLayout());

        // Carrega imagem de fundo
        ImageIcon imagemFundo = new ImageIcon("res\\telas\\inicio.png"); // Certifique-se de que o caminho está correto
        if (imagemFundo.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Erro ao carregar a imagem de fundo: res\\inicio.png");
        }
        JLabel labelFundo = new JLabel(imagemFundo);
        labelFundo.setBounds(0, 0, 1024, 728);

        // Painel para os botões, centralizados
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setOpaque(false); // Transparente para mostrar o fundo
        painelBotoes.setBounds(412, 400, 200, 150); // Centralizado na tela

        // Botão Jogar
        botaoJogar = new JButton("Jogar");
        botaoJogar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoJogar.setFont(new Font("Arial", Font.BOLD, 24)); // Fonte maior
        botaoJogar.setMaximumSize(new Dimension(200, 60)); // Dimensão máxima do botão
        botaoJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });
        painelBotoes.add(Box.createVerticalStrut(20));

        // Botão Instruções
        botaoInstrucoes = new JButton("Instruções");
        botaoInstrucoes.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoInstrucoes.setFont(new Font("Arial", Font.BOLD, 24)); // Fonte maior
        botaoInstrucoes.setMaximumSize(new Dimension(200, 60));
        botaoInstrucoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInstrucoes();
            }
        });

        // Adiciona os botões ao painel
        painelBotoes.add(botaoJogar);
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento entre botões
        painelBotoes.add(botaoInstrucoes);

        // Adiciona componentes ao painel principal
        add(painelBotoes, BorderLayout.CENTER); // Adiciona o painel de botões ao centro
        add(labelFundo, BorderLayout.CENTER); // Adiciona o fundo por último para não cobrir os botões

        setPreferredSize(new Dimension(1024, 728));
    }

    private void iniciarJogo() {
        frame.getContentPane().removeAll(); // Remove a tela de início
        Fase fase = new Fase(frame); // Cria uma instância da fase do jogo
        frame.setContentPane(fase); // Define a fase como o painel principal do frame
        frame.revalidate(); // Atualiza o layout
        frame.repaint(); // Redesenha a interface
        fase.requestFocusInWindow(); // Garante que o Fase receba o foco do teclado
    }

    private void mostrarInstrucoes() {
        frame.getContentPane().removeAll();  // Remove a tela de início
        frame.setContentPane(new TelaInstrucoes(frame)); // Define a tela de instruções como o painel principal
        frame.revalidate();
        frame.repaint();
    }
}
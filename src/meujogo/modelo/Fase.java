package meujogo.modelo;

import meujogo.visao.GameOver;
import meujogo.controle.Som;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Lixo> lixos;
    private boolean emJogo;
    private int faseAtual;  // Controle de fase atual
    private int lixosDestruídos;
    private Image imagemReciclável;
    private JFrame frame;
    private int tipo;
    private int quantidadeCorreta = 10;
    private Som musicaDeFundo;
    private Som somColisao;
    private Som somtiro;
    private List<Explosao> explosoes;
    private Image spritesheetExplosao;
    private boolean mostrarAviso = false;
    private Timer avisoTimer;

    public Fase(JFrame frame) {
        this.frame = frame;

        explosoes = new ArrayList<>();


        setFocusable(true);
        setDoubleBuffered(true);

        musicaDeFundo = new Som("res\\Sons\\musicaFundo.wav"); // Música de fundo
        somtiro = new Som("res\\Sons\\Somtiro.wav");
        somColisao = new Som("res\\Sons\\SomColisao.wav");
        musicaDeFundo.playLoop(); // Inicia a música de fundo


        ImageIcon referencia = new ImageIcon("res\\telas\\fundo.png");
        fundo = referencia.getImage();
        player = new Player(somtiro);
        player.load();

        addKeyListener(new TecladoAdapter());

        inicializarTimer();

        faseAtual = 1;
        lixosDestruídos = 0;
        inicializaInimigos();
        emJogo = true;

        // Inicializa a imagem do reciclável
        atualizarImagemReciclavel();




    }
    private void inicializarTimer() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        timer = new Timer(5, this);
        timer.start();
    }



    private void limparRecursos() {
        limparKeyListeners();
        if (lixos != null) {
            lixos.clear();
        }
        player = new Player(somtiro); // Reinicializar o player corretamente
        player.load();
        removeAll(); // Remove componentes visuais desnecessários
    }

    private void limparKeyListeners() {
        for (KeyListener listener : getKeyListeners()) {
            removeKeyListener(listener);
        }
    }

    public void inicializaInimigos() {
        if (lixos == null) {
            lixos = new ArrayList<>();
        }
        ImageIcon explosaoIcon = new ImageIcon("res\\modelos\\explosao.png");
        spritesheetExplosao = explosaoIcon.getImage();

        lixos.clear(); 

        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * 8000 + 1024);
            int y = (int) (Math.random() * 650 + 30);
            int tipoLixo = (int) (Math.random() * 4);

            if (tipoLixo == 0) {
                lixos.add(new Garrafa(x, y));
            } else if (tipoLixo == 1) {
                lixos.add(new Sacola(x, y));
            } else if (tipoLixo == 3) {
                lixos.add(new Latinha(x, y));
            }else {
                lixos.add(new Caixa(x,y));
            }
        }
    }

    public void finalizarJogo() {
        timer.stop();   // Para o Timer para evitar execução em segundo plano
        player = null;  // Remove referência ao jogador
        lixos.clear();  // Limpa a lista de lixos
        lixos = null;   // Remove a referência para ajudar o Garbage Collector
        musicaDeFundo.stop();
        System.gc();    // Solicita a liberação de memória
    }

    public void atualizarImagemReciclavel() {
        if (faseAtual == 1) {
            ImageIcon icon = new ImageIcon("res\\modelos\\Vidro.png");
            imagemReciclável = icon.getImage();
        } else if (faseAtual == 2) {
            ImageIcon icon = new ImageIcon("res\\modelos\\Plastico.png");
            imagemReciclável = icon.getImage();
        } else if (faseAtual == 3) {
            ImageIcon icon = new ImageIcon("res\\modelos\\Metal.png");
            imagemReciclável = icon.getImage();
        } else if (faseAtual == 4) {
            ImageIcon icon = new ImageIcon("res\\modelos\\Papel.png");
            imagemReciclável = icon.getImage();
        }
        repaint(); // Forçar a atualização da interface
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        if (emJogo) {
            g.drawImage(fundo, 0, 0, null);
            // Desenha os ícones de vida
            ImageIcon icone = new ImageIcon("res\\modelos\\vida.png");
            int vida = player.getVida();
            int posX = 10;  // Posição X inicial
            int posY = 20;  // Posição Y fixa
            int espacamento = 20;  // Espaçamento entre os ícones

            for (int i = 0; i < vida; i++) {
                g.drawImage(icone.getImage(), posX + (i * espacamento), posY, this);
            }

            g.drawImage(player.getImagem(), player.getX(), player.getY(),
                    player.getLargura(), player.getAltura(), this);

            List<Tiro> tiros = player.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro m = tiros.get(i);
                m.Load();
                g.drawImage(m.getImagen(), m.getX(), m.getY(), this);
            }

            for (int j = 0; j < lixos.size(); j++) {
                Lixo m = lixos.get(j);
                m.carregarImagem();
                g.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }
            for (Explosao explosao : explosoes) {
                explosao.desenhar(g);
            }

            if(faseAtual == 1){
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Destrua " + lixosDestruídos + "/10", 450, 50);
            }else if(faseAtual == 2){
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Destrua " + lixosDestruídos + "/12", 450, 50);
            }else if(faseAtual == 3){
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Destrua " + lixosDestruídos + "/14", 450, 50);
            }else if(faseAtual == 4){
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Destrua " + lixosDestruídos + "/16", 450, 50);
            }


            if (lixosDestruídos < -3 && !mostrarAviso) {
                mostrarAviso = true;

                if (avisoTimer != null && avisoTimer.isRunning()) {
                    avisoTimer.stop(); // Garante que o Timer anterior seja interrompido
                }

                avisoTimer = new Timer(5000, new ActionListener() { // Agora com 5 segundos
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarAviso = false;
                        repaint(); // Garante que a tela seja atualizada para remover o aviso
                        avisoTimer.stop(); // Para o Timer
                    }
                });

                avisoTimer.setRepeats(false); // Executa apenas uma vez
                avisoTimer.start();
            }

            if (mostrarAviso) {
                switch (faseAtual) {
                    case 1:
                        g.drawString("Lembre-se, lixeira verde = VIDRO!", 360, 90);
                        break;
                    case 2:
                        g.drawString("Lembre-se, lixeira laranja = PLÁSTICO!", 360, 90);
                        break;
                    case 3:
                        g.drawString("Lembre-se, lixeira amarela = METAL!", 360, 90);
                        break;
                    case 4:
                        g.drawString("Lembre-se, lixeira azul = PAPEL!", 360, 90);
                        break;
                }
            }

            // Exibe a imagem do reciclável
            if (imagemReciclável != null) {
                g.drawImage(imagemReciclável, 600, 20, 50, 59, this);
            }
        }else{
            finalizarJogo();
        }

        if(player.getVida() <= 0){
            tipo = 1;
            emJogo = false;
            frame.getContentPane().removeAll();
            frame.setContentPane(new GameOver(frame, this,tipo)); // Passa a referência da fase
            frame.revalidate();
            frame.repaint();
            return;

        }

        if (lixosDestruídos <= -5) {
            tipo = 2;
            emJogo = false;
            frame.getContentPane().removeAll();
            frame.setContentPane(new GameOver(frame, this,tipo)); // Passa a referência da fase
            frame.revalidate();
            frame.repaint();
            return;
        }
        if(faseAtual > 4){
            tipo = 3;
            emJogo = false;
            frame.getContentPane().removeAll();
            frame.setContentPane(new GameOver(frame, this,tipo)); // Passa a referência da fase
            frame.revalidate();
            frame.repaint();
            return;
        }


    }





    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        for (int i = 0; i < player.getTiros().size(); i++) {
            Tiro tiro = player.getTiros().get(i);
            if (tiro.isVisible()) {
                tiro.Update();
            } else {
                player.getTiros().remove(i);
                i--;
            }
        }

        for (int i = 0; i < lixos.size(); i++) {
            Lixo lixo = lixos.get(i);
            if (lixo.isVisivel()) {
                lixo.atualizar();
            }
        }
        for (int i = 0; i < explosoes.size(); i++) {
            explosoes.get(i).atualizar();
            if (explosoes.get(i).terminou()) {
                explosoes.remove(i);
                i--;  // Ajusta o índice após remoção
            }
        }
        checarColisoes();
        repaint(); // Chama o repaint apenas uma vez
    }

    public void avancarFase() {
        faseAtual++;
        lixosDestruídos = 0;
        quantidadeCorreta+=2;

        // Inicializa os inimigos da nova fase e atualiza a imagem do reciclável
        inicializaInimigos();
        atualizarImagemReciclavel();

        // Garantir atualização gráfica
        revalidate();
        repaint();
    }


    private boolean lixoCorretoParaDestruir(Lixo lixo) {
        if (faseAtual == 1 && lixo instanceof Garrafa) {
            return true;  // Fase 1: Destruir apenas garrafas
        } else if (faseAtual == 2 && lixo instanceof Sacola) {
            return true;  // Fase 2: Destruir apenas sacolas
        } else if (faseAtual == 3 && lixo instanceof Latinha) {
            return true;  // Fase 3: Destruir apenas latas de alumínio
        } else if(faseAtual == 4 && lixo instanceof Caixa){
            return true; // Fase 4: destruir apenas caixas de papel
        }
        return false;  // Lixo errado para destruir nesta fase
    }

    public void checarColisoes() {
        Rectangle formaSub = player.getBounds();
        Rectangle formaLixo1;
        Rectangle formaTiro;

        // Verifica se o lixo colidiu com o jogador
        for (int i = 0; i < lixos.size(); i++) {
            Lixo tempLixo = lixos.get(i);
            formaLixo1 = tempLixo.getBounds();

            // Checa colisão com o jogador
            if (formaSub.intersects(formaLixo1) && tempLixo.isVisivel()) {
                somColisao.play();
                int explosaoX = tempLixo.getX() + (50 / 2) - 32;
                int explosaoY = tempLixo.getY() + (50 / 2) - 32 - 10; // Explosão um pouco mais alta
                explosoes.add(new Explosao(explosaoX, explosaoY, spritesheetExplosao, 2, 5));
                explosoes.add(new Explosao(explosaoX, explosaoY, spritesheetExplosao, 2, 5));
                player.setVida(player.getVida() - 1);
                if (lixoCorretoParaDestruir(tempLixo)) {
                    tempLixo.setVisivel(false);  // Destrói o lixo correto
                    // Ganha pontos por destruir o lixo correto
                    lixosDestruídos++;
                } else {
                    // Se o jogador atingiu um lixo errado
                    tempLixo.setVisivel(false);
                    lixosDestruídos--;
                    // Perde pontos por errar
                }

            }
        }

        // Verifica colisão com os tiros
        List<Tiro> tiros = player.getTiros();
        for (int j = 0; j < tiros.size(); j++) {
            Tiro tempTiro = tiros.get(j);
            formaTiro = tempTiro.getBounds();
            for (int p = 0; p < lixos.size(); p++) {
                Lixo tempLixo = lixos.get(p);
                formaLixo1 = tempLixo.getBounds();
                if (formaTiro.intersects(formaLixo1)) {
                    somColisao.play();
                    int explosaoX = tempLixo.getX() + (50 / 2) - 32;
                    int explosaoY = tempLixo.getY() + (50 / 2) - 32 - 10; // Explosão um pouco mais alta
                    explosoes.add(new Explosao(explosaoX, explosaoY, spritesheetExplosao, 2, 5));
                    explosoes.add(new Explosao(explosaoX, explosaoY, spritesheetExplosao, 2, 5));
                    if (lixoCorretoParaDestruir(tempLixo)) {
                        tempLixo.setVisivel(false);
                        tempTiro.setVisible(false);
                        lixosDestruídos++;
                    } else {
                        tempLixo.setVisivel(false);
                        tempTiro.setVisible(false);
                        lixosDestruídos--;
                    }
                }
            }
        }


        for (int i = 0; i < lixos.size(); i++) {
            Lixo tempLixo = lixos.get(i);
            if (!tempLixo.isVisivel()) {

                int x = (int) (Math.random() * 8000 + 1024);
                int y = (int) (Math.random() * 650 + 30);
                tempLixo.setX(x);
                tempLixo.setY(y);
                tempLixo.setVisivel(true);
            }
        }

        // Verifica se a quantidade correta de lixos foi destruída
        if (lixosDestruídos >= quantidadeCorreta) {
            avancarFase();
        }
    }

    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keypressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyrelease(e);
        }
    }
}
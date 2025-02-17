package Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Game.ChoseView;
import Game.Parametre;
import Game.View;
import java.nio.file.Paths;
import Game.Player.Player;

public class GameOver {
    private JFrame frame;
    private Parametre param;
    private int score;

    public GameOver(int score, Parametre param) {
        this.score = score;
        this.param = param;
        // param.makeSound("ressources\\musiques\\musicJeu.wav");
        // Création de la fenêtre
        frame = new JFrame("Menu avec image en arrière-plan");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Création du panneau principal avec une image en arrière-plan
        JPanel panel = new JPanel() {
            // Surcharge de la méthode paintComponent pour dessiner l'image en arrière-plan
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImg = new ImageIcon(Paths.get("ressources", "menu", "over.jpeg").toString());
                g.drawImage(backgroundImg.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(null); // Utilisation d'un layout null pour positionner les boutons manuellement

        // Création des boutons
        JButton button1 = new JButton("REJOUER");
        JButton button2 = new JButton("PARAMETRES");
        JButton button3 = new JButton("MENU PRINCIPAL");
        JButton button4 = new JButton("QUITTER");
        button4.addActionListener(event -> {
            System.exit(0);
        });
        button1.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = graphics.getDefaultScreenDevice();
            Player player = new Player();
            View screen = new View(player, param);
            device.setFullScreenWindow(screen);
            screen.setVisible(true);
            screen.animate();
        });
        button3.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            Game test = new Game(param, null, false);
            test.getFrame().setVisible(true);
        });
        button2.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            ChoseView test = new ChoseView(param);
            test.getFrame().setVisible(true);
        });
        button4.addActionListener(event -> {
            
            frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        });
        makeEffet(button1);
        makeEffet(button2);
        makeEffet(button3);
        makeEffet(button4);
        bordure(button2);
        bordure(button1);
        bordure(button3);
        bordure(button4);
        // Positionnement des boutons
        button1.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2), 250, 50);
        button2.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2) + 100, 250, 50);
        button3.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2) + 200, 250, 50);
        button4.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2) + 300, 250, 50);

        // Ajout des boutons au panneau principal
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        JPanel over = makeOver();
        over.setBounds((frame.getWidth() / 2), (frame.getHeight() / 4), (frame.getWidth()), 50);
        panel.add(over);

        // Ajout du panneau principal à la fenêtre
        frame.setContentPane(panel);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(frame);
        frame.setVisible(false);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel makeOver() {
        JLabel gameOverLabel;
        JPanel over = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect((frame.getWidth()), (frame.getHeight() / 4), 2 * frame.getWidth(), 100);
            }
        };
        over.setLayout(new BorderLayout());
        gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        JLabel labelScore = new JLabel("SCORE : " + score, SwingConstants.CENTER);
        labelScore.setFont(new Font("Arial", Font.BOLD, 50));
        labelScore.setForeground(Color.RED);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        gameOverLabel.setForeground(Color.RED);

        // Centrer le label à l'intérieur du JPanel "over"
        over.add(gameOverLabel, BorderLayout.CENTER);
        over.add(labelScore, BorderLayout.EAST);
        return over;
    }

    public void makeEffet(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.YELLOW); // Changement de couleur au survol
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE); // Retour à la couleur initiale
            }
        });
    }

    public void bordure(JButton button2) {
        // Création d'une bordure arrondie et ombrée
        button2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), // Bordure extérieure
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 15, 5, 15), // Marge intérieure
                        BorderFactory.createLineBorder(Color.WHITE, 2) // Bordure intérieure
                )));
        button2.setFont(new Font("Arial", Font.BOLD, 14));
    }

}

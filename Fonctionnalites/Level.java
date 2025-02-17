package Fonctionnalites;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Game.Parametre;
import Game.View;
import Game.Game.Game;
import Game.Player.Player;

public class Level {
    private JFrame frame;
    private Parametre param;
    private Player player;
    private int level;

    public Level(int level, Parametre param, Player player) {
        this.level = level;
        this.param = param;
        this.player = player;

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
                ImageIcon backgroundImg = new ImageIcon("ressources\\menu\\over.jpeg");
                g.drawImage(backgroundImg.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(null); // Utilisation
                               // d'un
                               // layout
                               // null
                               // pour
                               // positionner
                               // les
                               // boutons
                               // manuellement

        // Création des boutons
        JButton button1 = new JButton("PASSER AU LEVEL SUIVANT");
        JButton button2 = new JButton("MENU PRINCIPAL");
        JButton button4 = new JButton("QUITTER");
        button4.addActionListener(event -> {
            System.exit(0);
        });
        button1.addActionListener(event -> {
            GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = graphics.getDefaultScreenDevice();
            Parametre testparam = new Parametre(param.getModeDeJeu(), param.getModeView(), param.getDiffucult(),
                    param.getTextOrImage());
            View screen = new View(player, testparam, level + 1, Serialize.deserialize());
            device.setFullScreenWindow(screen);
            screen.setVisible(true);
            screen.animate();
        });
        button2.addActionListener(event -> {
            Game test = new Game();
            test.getFrame().setVisible(true);
        });

        makeEffet(button1);
        makeEffet(button2);
        makeEffet(button4);
        bordure(button2);
        bordure(button1);
        bordure(button4);
        // Positionnement des boutons
        button1.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2), 250, 50);
        button2.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2) + 100, 250, 50);
        button4.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2) + 200, 250, 50);

        // Ajout des boutons au panneau principal
        panel.add(button1);
        panel.add(button2);
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

        gameOverLabel = new JLabel("FELICITATIONS LEVEL " + level + " REUSSIE  ", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setForeground(Color.RED);

        // Centrer le label à l'intérieur du JPanel "over"
        over.add(gameOverLabel, BorderLayout.CENTER);
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

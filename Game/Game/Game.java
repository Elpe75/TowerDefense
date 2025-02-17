package Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Fonctionnalites.*;
import Game.ChoseView;
// import Game.ChoseView;
import Game.Parametre;
import Game.View;
import Game.Player.Player;
import java.nio.file.Paths;

public class Game {
    private JFrame frame;
    private Parametre param;
    private int bestScore;

    public Game(Parametre param, String saved, boolean son) {
        this.param = param;
        bestScore = Serialize.deserialize();
        if (son)
            this.param.makeSound(Paths.get("ressources", "musiques", "musicPacman.wav").toString());
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
                ImageIcon backgroundImg = new ImageIcon(Paths.get("ressources", "menu", "menuP.jpg").toString());
                g.drawImage(backgroundImg.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        panel.setLayout(null); // Utilisation d'un layout null pour positionner les boutons manuellement

        // Création des boutons
        JButton button1 = new JButton("JOUER");
        JButton button2 = new JButton("PARAMETRES");
        JButton button3 = new JButton("CREDITS");
        JButton button4 = new JButton("QUITTER");
        JButton button6 = new JButton("PARTIES SAUVEGARDEES");
        JButton sauvegarde;
        if (saved != null) {
            sauvegarde = new JButton("SAUVEGARDE LA PARTIE ");
            sauvegarde.setBounds((frame.getWidth() / 2) + 200, (frame.getHeight() / 2) + 300, 250, 50);
            makeEffet(sauvegarde);
            bordure(sauvegarde);
            sauvegarde.addActionListener(event -> {
                frame.setVisible(false);
                frame.dispose();
                PageSauvegarde result = new PageSauvegarde();
                result.getFrame().setVisible(true);
            });
            panel.add(sauvegarde);
        }
        button4.addActionListener(event -> {
            Serialize.serialize(bestScore);
            System.exit(0);
        });
        button1.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            Player player = new Player();
            View test = new View(player, param);
            GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = graphics.getDefaultScreenDevice();
            device.setFullScreenWindow(test);
            test.setVisible(true);
            test.animate();
        });
        button2.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            ChoseView test = new ChoseView(param);
            test.getFrame().setVisible(true);
        });
        makeEffet(button6);
        bordure(button6);
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
        button4.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2) + 200, 250, 50);
        button6.setBounds((frame.getWidth() / 2) + 300, (frame.getHeight() / 2) + 100, 250, 50);
        button3.setBounds((frame.getWidth() / 2) + 300, (frame.getHeight() / 2), 250, 50);
        JPanel best = bestScore();
        best.setBounds((frame.getWidth() / 2), (frame.getHeight()) + 100, (frame.getWidth()), 50);
        // Ajout des boutons au panneau principal
        panel.add(button1);
        panel.add(button2);
        //panel.add(button3);
        panel.add(button4);
        // panel.add(button6);
        panel.add(best);

        // Ajout du panneau principal à la fenêtre EN PLEIN ECRAN
        frame.setContentPane(panel);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(frame);
        frame.setVisible(false);
    }

    public Game() {
        this(new Parametre(Serialize.deserialize()), null, false);
    }

    // public Game(boolean modeView, boolean modeDeJeu) {
    // param.setModeView(modeView);
    // param.setModeJeu();
    // this(new Parametre(), null,modeView, modeDeJeu, false);
    // }

    public JPanel bestScore() {
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

        gameOverLabel = new JLabel("BEST SCORE : " + bestScore, SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
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

    public JFrame getFrame() {
        return frame;
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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        Parametre param = new Parametre(true, true, 1, true);
                        Game frame = new Game(param, null, true);
                        frame.getFrame().setVisible(true);
                    }
                });
    }

}

package Game;

import javax.swing.*;

import Fonctionnalites.Serialize;
import Game.Game.Game;
import Game.Player.Player;
import java.nio.file.Paths;
import java.awt.*;

public class ChoseView {
    private JFrame frame;
    private Parametre param;

    public ChoseView(Parametre param) {
        this.param = param;
        // param.makeSound("ressources\\musiques\\musicJeu.wav");
        // Création de la fenêtre
        frame = new JFrame("Menu avec image en arrière-plan");
        frame.setVisible(false);
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
        JButton button1 = new JButton("QUITTER");
        JButton button4 = new JButton("MENU");
        JButton button5 = new JButton("MODE MARATHON");
        JButton button6 = new JButton("MODE NORMAL");
        JButton button7 = new JButton("BACULER EN MODE TEXT/IMAGE ");

        button7.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            param.setTextOrImage(!param.getTextOrImage());
            ChoseView game = new ChoseView(param);
            game.getFrame().setVisible(true);
        });
        button4.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            Game game = new Game(param, null, false);
            game.getFrame().setVisible(true);
        });
        button1.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        });
        // MODE NORMAL
        button6.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            setMode(false);
            ChoseView game = new ChoseView(param);
            JPanel text = makeOver("MODE CHANGE / MODE NORMAL");
            text.setBounds((frame.getWidth() / 2) + 50, (frame.getHeight()), frame.getWidth(), 50);
            // game.getFrame().add(text);
            game.getFrame().setVisible(true);
            // System.out.println("On change de mode normal");
        });
        /// MODE MARATHON
        button5.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            setMode(true);
            ChoseView game = new ChoseView(param);
            JPanel text = makeOver("MODE CHANGE / MODE MARATHON");
            text.setBounds((frame.getWidth() / 2) + 50, (frame.getHeight()), frame.getWidth(), 50);
            // game.getFrame().add(text);
            game.getFrame().setVisible(true);
            // System.out.println("On change de mode marathon");
        });

        // Positionnement des boutons
        button4.setBounds((frame.getWidth() / 4), frame.getHeight() / 2, 150, 50);
        button1.setBounds((frame.getWidth() / 4), frame.getHeight() / 2 + 100, 150, 50);
        button5.setBounds((frame.getWidth() / 4) - 30, frame.getHeight() / 2 + 200, 200, 50);
        button6.setBounds((frame.getWidth() / 4), frame.getHeight() / 2 + 300, 150, 50);
        button7.setBounds((frame.getWidth() / 4), frame.getHeight() / 2 + 400, 350, 50);
        JPanel view1 = new JPanel(new GridLayout(1, 1));
        view1.setBounds((frame.getWidth() / 2), (frame.getHeight() / 2), 300, 200);
        view1.add(new View().creatJPanel());
        JPanel view2 = new JPanel(new GridLayout(1, 1));
        view2.setBounds((frame.getWidth()), (frame.getHeight() / 2), 300, 200);
        view2.add(new View(new Player(),
                new Parametre(param.getModeDeJeu(), false, param.getDiffucult(), param.getTextOrImage()))
                .creatJPanel());
        // VIEW 2
        JButton button2 = new JButton("VIEW 2");
        button2.setBounds((frame.getWidth()) + 25, (frame.getHeight() - 100), 250, 50);
        button2.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            setView(false);
            ChoseView game = new ChoseView(param);
            JPanel text = makeOver("VIEW  CHANGE / VIEW 2");
            text.setBounds((frame.getWidth() / 2) + 50, (frame.getHeight()), frame.getWidth(), 50);
            // game.getFrame().add(text);
            game.getFrame().setVisible(true);
            // System.out.println("On change de view2");
        });
        // VIEW 1
        JButton button = new JButton("VIEW 1");
        button.setBounds((frame.getWidth() / 2) + 25, (frame.getHeight() - 100), 250, 50);
        button.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            setView(true);
            ChoseView game = new ChoseView(param);
            JPanel text = makeOver("VIEW  CHANGE / VIEW 1");
            text.setBounds((frame.getWidth() / 2) + 50, (frame.getHeight()), frame.getWidth(), 50);
            // game.getFrame().add(text);
            game.getFrame().setVisible(true);
            // System.out.println("On change de view1");
        });
        JPanel difficulte = makeOver("NIVEAU DE DIFFICULTE");
        difficulte.setBounds((frame.getWidth() / 2), (frame.getHeight() + 100), frame.getWidth(), 50);
        panel.add(difficulte);
        JButton facile = new JButton("Facile");
        JButton moyen = new JButton("Moyen");
        JButton difficile = new JButton("Difficile");
        facile.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            setDiff(1);
            param.setDifficult(1);
            ChoseView game = new ChoseView(param);
            game.getFrame().setVisible(true);
        });
        moyen.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            setDiff(2);
            param.setDifficult(2);
            ChoseView game = new ChoseView(param);
            game.getFrame().setVisible(true);
        });
        difficile.addActionListener(event -> {
            frame.setVisible(false);
            frame.dispose();
            setDiff(3);
            param.setDifficult(3);
            ChoseView game = new ChoseView(param);
            game.getFrame().setVisible(true);;
        });

        JPanel niveau = new JPanel(new GridLayout(1, 3));
        niveau.add(facile);
        niveau.add(moyen);
        niveau.add(difficile);
        bordure(facile);
        bordure(difficile);
        bordure(moyen);

        niveau.setBounds((frame.getWidth() / 2), (frame.getHeight() + 180), frame.getWidth(), 50);
        // makeEffet(button1);

        bordure(button1);
        bordure(button2);
        bordure(button);
        bordure(button4);
        bordure(button5);
        bordure(button7);
        bordure(button6);
        panel.setLayout(null); // Utilisation d'un layout null pour positionner les boutons manuellement
        panel.add(button1);
        panel.add(view1);
        panel.add(view2);
        panel.add(button2);
        panel.add(button);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(niveau);
        JPanel etatAct = etatActuel();
        etatAct.setBounds(50, 50, frame.getWidth(), 50);
        panel.add(etatAct);
        button1.addActionListener(event -> {
            System.exit(0);
        });
        // Ajout du panneau principal à la fenêtre
        frame.setContentPane(panel);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(frame);
        frame.setVisible(false);
    }

    public void setView(boolean test) {
        param.setModeView(test);
    }

    public void setMode(boolean mode) {
        param.setModeJeu(mode);
    }

    public JFrame getFrame() {
        return frame;
    }

    public String[] actuel() {
        String[] result = new String[3];
        String modeStr = param.getModeDeJeu() ? "MARATHON" : "NORMAL";
        String viewStr = param.getModeView() ? "VIEW 1 " : "VIEW 2 ";
        String diffStr;
        if (param.getDiffucult() == 1)
            diffStr = "FACILE";
        if (param.getDiffucult() == 2)
            diffStr = "MOYEN";
        else
            diffStr = "DIFFICILE";
        result[0] = modeStr;
        result[1] = viewStr;
        result[2] = diffStr;
        return result;
    }

    public void setDiff(int x) {
        param.setDifficult(x);
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

    public JPanel makeOver(String mess) {
        JLabel gameOverLabel;
        JPanel over = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect((frame.getWidth()), (frame.getHeight() / 4), frame.getWidth(), 100);
            }
        };
        over.setLayout(new BorderLayout());

        gameOverLabel = new JLabel(mess, SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setForeground(Color.RED);

        // Centrer le label à l'intérieur du JPanel "over"
        over.add(gameOverLabel, BorderLayout.CENTER);
        return over;
    }

    private JPanel etatActuel() {
        JPanel actuel = new JPanel(new GridLayout(1, 5));
        String[] act = actuel();
        String text = param.getTextOrImage() ? "VERSION IMAGE " : "VERSION  TEXTE";
        JLabel def = new JLabel("PARAMETRAGE ACTUEL  /: ");
        JLabel mode = new JLabel(" MODE :" + act[0]);
        JLabel view = new JLabel(" VIEW : " + act[1]);
        JLabel diff = new JLabel(" NIVEAU : " + act[2]);
        actuel.add(def);
        actuel.add(diff);
        actuel.add(view);
        actuel.add(mode);
        actuel.add(new JLabel(text));
        return actuel;
    }

    // public static void main(String[] args) {
    // ChoseView test = new ChoseView(false, false, new Parametre());
    // test.getFrame();
    // }
}

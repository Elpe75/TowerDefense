package Fonctionnalites;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PageSauvegarde {
    private JFrame frame;
    private LinkedList<Sauvegarde> listSaved = new LinkedList<>();

    public PageSauvegarde() {
        // Création de la fenêtre
        frame = new JFrame("Menu avec image en arrière-plan");
        listSaved = Sauvegarde.getList();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        // Création du panneau principal avec une image en arrière-plan
        JPanel panel = new JPanel() {
            // Surcharge de la méthode paintComponent pour dessiner l'image en arrière-plan
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImg = new ImageIcon("ressources\\\\menu\\\\menuP.jpg");
                g.drawImage(backgroundImg.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        panel.setLayout(null); // Utilisation d'un layout null pour positionner les boutons manuellement
        JPanel allSaved = new JPanel(new GridLayout(listSaved.size() / 2 + 1, 2));
        // Ajout et placement des composants

        int num = listSaved.size() - 1;
        while (num > 0) {
            allSaved.add(makeSaved(listSaved.get(num)));
            num--;
        }
        // EN PLEIN ECRAN
        // Ajout du panneau principal à la fenêtre EN PLEIN ECRAN
        allSaved.setBounds(50, 50, frame.getWidth(), frame.getHeight());
        allSaved.add(new JButton("TEXT "));
        panel.add(allSaved);
        frame.setContentPane(panel);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(frame);
        frame.setVisible(false);

    }

    public Frame getFrame() {
        return frame;
    }

    public JLabel descript(Sauvegarde saved) {
        String mode = saved.getView().getMode() ? "MARATHON " : "NORMAL";
        String result = "Score : " + saved.getPlayer().getScore() + " Level :" + saved.getView().getLevel() + " Mode : "
                + mode;
        return new JLabel(result);
    }

    public JPanel makeSaved(Sauvegarde saved) {
        JPanel result = new JPanel(new GridLayout(2, 1));
        JButton replay = new JButton("Replay");
        JButton delete = new JButton("Supprimer");
        result.add(replay);
        result.add(delete);
        result.add(descript(saved));
        return result;
    }
}
